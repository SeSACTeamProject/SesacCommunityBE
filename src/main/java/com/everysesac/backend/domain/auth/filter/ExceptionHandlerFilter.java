package com.everysesac.backend.domain.auth.filter;

import com.everysesac.backend.global.exception.ErrorCode;
import com.everysesac.backend.global.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;


@Component
@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<Class<? extends AuthenticationException>, ErrorCode> AUTH_EXCEPTION_ERROR_CODE_MAP = Map.of(
            BadCredentialsException.class, ErrorCode.BAD_CREDENTIALS,
            DisabledException.class, ErrorCode.ACCOUNT_DISABLED,
            LockedException.class, ErrorCode.ACCOUNT_LOCKED,
            AccountExpiredException.class, ErrorCode.ACCOUNT_EXPIRED,
            CredentialsExpiredException.class, ErrorCode.CREDENTIALS_EXPIRED
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthenticationException ex) {
            log.error("Authentication exception occurred: {}", ex.getMessage());
            handleAuthenticationException(response, ex);
        } catch (AccessDeniedException ex) {
            log.error("Access denied exception occurred: {}", ex.getMessage());
            handleAccessDeniedException(response, ex);
        } catch (IllegalArgumentException ex) {
            log.error("Illegal argument exception occurred: {}", ex.getMessage());
            handleCustomException(response, ErrorCode.ILLEGAL_ARGUMENT_ERROR,ex);
        } catch (Exception ex) {
            log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
            handleCustomException(response, ErrorCode.INTERNAL_SERVER_ERROR,ex);
        }
    }

    private void handleAuthenticationException(HttpServletResponse response, AuthenticationException ex) throws IOException {
        // 기본값: AUTHENTICATION_FAILED
        ErrorCode errorCode = AUTH_EXCEPTION_ERROR_CODE_MAP.getOrDefault(ex.getClass(), ErrorCode.AUTHENTICATION_FAILED);

        setErrorResponse(response, errorCode, ex);
    }

    private void handleAccessDeniedException(HttpServletResponse response, AccessDeniedException ex) throws IOException {
        setErrorResponse(response, ErrorCode.ACCESS_DENIED,ex);
    }

    private void handleCustomException(HttpServletResponse response, ErrorCode errorCode, Exception ex) throws IOException {
        setErrorResponse(response, errorCode, ex);
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode,Exception ex) throws IOException {
        response.setStatus(errorCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // 에러 응답 생성
        ErrorResponse errorResponse = new ErrorResponse(
                "error",
                errorCode.getCode(),
                ex.getMessage() != null ? ex.getMessage() : ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
                null
        );

        // JSON 응답 작성
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
