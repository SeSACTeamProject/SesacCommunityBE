package com.everysesac.backend.domain.auth.filter;

import com.everysesac.backend.domain.auth.jwt.JWTUtil;
import com.everysesac.backend.domain.auth.repository.RefreshRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class CustomLogoutFilter extends GenericFilterBean {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // URI 체크
            if (isLogoutRequest(request, filterChain,response)) return;

            // POST 요청인지 확인
            if (isPostRequest(request, filterChain,response)) return;

            // Refresh Token 가져오기
            String refresh = getRefreshToken(request);

            // Refresh Token 검증
            validateRefreshToken(refresh);

            // 로그아웃 처리 (Refresh Token 삭제 및 쿠키 초기화)
            processLogout(response, refresh);

        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token during logout: {}", e.getMessage());
            throw new SessionAuthenticationException("Refresh token has expired.");
        } catch (AuthenticationException e) {
            log.error("Authentication error during logout: {}", e.getMessage());
            throw e; // Spring Security가 처리하도록 위임
        } catch (IllegalArgumentException e) {
            log.error("Invalid input during logout: {}", e.getMessage());
            throw new InsufficientAuthenticationException(e.getMessage());
        }
    }

    private void validateRefreshToken(String refresh) {
        if (refresh == null || refresh.isEmpty()) {
            throw new IllegalArgumentException("Refresh token is missing.");
        }

        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            throw new SessionAuthenticationException("Refresh token has expired.");
        }

        String category = jwtUtil.getCategory(refresh);
        if (!"refresh".equals(category)) {
            throw new IllegalArgumentException("Invalid refresh token category.");
        }

        boolean exists = refreshRepository.existsByRefresh(refresh);
        if (!exists) {
            throw new IllegalArgumentException("Refresh token not found in the database.");
        }
    }

    private String getRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;

        for (Cookie cookie : cookies) {
            if ("refresh".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private boolean isPostRequest(HttpServletRequest request, FilterChain filterChain, HttpServletResponse response) throws IOException, ServletException {
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }

    private boolean isLogoutRequest(HttpServletRequest request, FilterChain filterChain, HttpServletResponse response) throws IOException, ServletException {
        String requestUri = request.getRequestURI();
        if (!"/logout".equals(requestUri)) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }

    private void processLogout(HttpServletResponse response, String refresh) {
        // Refresh Token DB에서 삭제
        refreshRepository.deleteByRefresh(refresh);

        // Refresh Token 쿠키 초기화
        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);

        // 로그아웃 성공 응답 설정
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
