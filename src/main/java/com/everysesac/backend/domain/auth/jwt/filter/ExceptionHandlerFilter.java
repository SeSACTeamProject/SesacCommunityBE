package com.everysesac.backend.domain.auth.jwt.filter;

import com.everysesac.backend.global.exception.ErrorCode;
import com.everysesac.backend.global.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 다음 필터로 요청 전달
            filterChain.doFilter(request, response);
        } catch (AuthenticationException ex) {
            log.error("Authentication exception occurred: {}", ex.getMessage());
            handleAuthenticationException(response, ex);
        } catch (AccessDeniedException ex) {
            log.error("Access denied exception occurred: {}", ex.getMessage());
            handleAccessDeniedException(response, ex);
        } catch (IllegalArgumentException ex) {
            log.error("Illegal argument exception occurred: {}", ex.getMessage());
            handleCustomException(response, ErrorCode.ILLEGAL_ARGUMENT_ERROR);
        } catch (Exception ex) {
            log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
            handleCustomException(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void handleAuthenticationException(HttpServletResponse response, AuthenticationException ex) throws IOException {
        ErrorCode errorCode;

        if (ex instanceof BadCredentialsException) {
            errorCode = ErrorCode.BAD_CREDENTIALS;
        } else if (ex instanceof DisabledException) {
            errorCode = ErrorCode.ACCOUNT_DISABLED;
        } else if (ex instanceof LockedException) {
            errorCode = ErrorCode.ACCOUNT_LOCKED;
        } else if (ex instanceof AccountExpiredException) {
            errorCode = ErrorCode.ACCOUNT_EXPIRED;
        } else if (ex instanceof CredentialsExpiredException) {
            errorCode = ErrorCode.CREDENTIALS_EXPIRED;
        } else {
            errorCode = ErrorCode.AUTHENTICATION_FAILED; // 기본 인증 실패 에러 코드
        }

        setErrorResponse(response, errorCode);
    }

    private void handleAccessDeniedException(HttpServletResponse response, AccessDeniedException ex) throws IOException {
        setErrorResponse(response, ErrorCode.ACCESS_DENIED);
    }

    private void handleCustomException(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        setErrorResponse(response, errorCode);
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // 에러 응답 생성
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status("error")
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        // JSON 응답 작성
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
