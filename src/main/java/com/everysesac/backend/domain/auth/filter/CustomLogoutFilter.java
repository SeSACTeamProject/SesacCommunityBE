package com.everysesac.backend.domain.auth.filter;

import com.everysesac.backend.global.exception.CustomException;
import com.everysesac.backend.global.exception.ErrorCode;
import com.everysesac.backend.domain.auth.repository.RefreshRepository;
import com.everysesac.backend.domain.auth.jwt.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        // URI 체크
        if (checkUri(request, response, filterChain)) return;

        // POST 요청인지 확인
        if (checkPostMethod(request, response, filterChain)) return;

        // Refresh Token 가져오기
        String refresh = getRefreshToken(request);

        // Refresh Token null 체크
        refreshNullCheck(refresh);

        // Refresh Token 만료 여부 확인
        expiredCheck(refresh);

        // Refresh Token 카테고리 확인
        categoryCheck(refresh);

        // Refresh Token DB 존재 여부 확인
        dbCheck(refresh);

        // 로그아웃 처리 (Refresh Token 삭제 및 쿠키 초기화)
        processLogout(response, refresh);
    }

    private void refreshNullCheck(String refresh) {
        if (refresh == null) {
            throw new CustomException(ErrorCode.INVALID_BEARER_TOKEN);
        }
    }

    private void dbCheck(String refresh) {
        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if (!isExist) {
            throw new CustomException(ErrorCode.AUTHENTICATION_FAILED);
        }
    }

    private void categoryCheck(String refresh) {
        String category = jwtUtil.getCategory(refresh);
        if (!"refresh".equals(category)) {
            throw new CustomException(ErrorCode.AUTHENTICATION_FAILED);
        }
    }

    private void expiredCheck(String refresh) {
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.JWT_VALIDATION_FAILED);
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

    private boolean checkPostMethod(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }

    private boolean checkUri(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
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
