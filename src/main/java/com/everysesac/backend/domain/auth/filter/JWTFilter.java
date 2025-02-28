package com.everysesac.backend.domain.auth.filter;

import com.everysesac.backend.domain.auth.dto.CustomUserDetails;
import com.everysesac.backend.domain.auth.exception.AccessTokenException;
import com.everysesac.backend.domain.auth.jwt.JWTUtil;
import com.everysesac.backend.global.exception.ErrorCode;
import com.everysesac.backend.domain.auth.exception.RefreshTokenException;
import com.everysesac.backend.domain.user.entity.Role;
import com.everysesac.backend.domain.user.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("access");
        log.info("accessToken : {}",accessToken );

        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            validateAccessToken(accessToken);
            setAuthentication(accessToken);
            filterChain.doFilter(request, response); // 다음 필터로 이동

        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token: {}", e.getMessage());
            throw new AccessTokenException(ErrorCode.EXPIRED_ACCESS_TOKEN);

        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT Token: {}", e.getMessage());
            throw new AccessTokenException(ErrorCode.JWT_VALIDATION_FAILED);
        }
    }

    private void validateAccessToken(String accessToken) {
        jwtUtil.isExpired(accessToken); // 만료 여부 확인
        String category = jwtUtil.getCategory(accessToken);
        if (!"access".equals(category)) {
            throw new AccessTokenException(ErrorCode.INVALID_BEARER_TOKEN);
        }
    }

    private void setAuthentication(String accessToken) {
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        User user = User.builder()
                .username(username)
                .role(Role.valueOf(role))
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                customUserDetails,
                null,
                customUserDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
