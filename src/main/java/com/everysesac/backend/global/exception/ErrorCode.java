package com.everysesac.backend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Validation Errors
    INVALID_REQUEST_PARAMETER("E4001", "잘못된 요청 파라미터입니다.", HttpStatus.BAD_REQUEST),
    FIELD_VALIDATION_ERROR("E4002", "필드 검증 오류가 발생했습니다.", HttpStatus.BAD_REQUEST),
    ILLEGAL_ARGUMENT_ERROR("E4003", "유효하지 않은 인자가 제공되었습니다.", HttpStatus.BAD_REQUEST),

    // Authentication Errors
    AUTHENTICATION_FAILED("E4010", "인증에 실패했습니다.", HttpStatus.UNAUTHORIZED),
    BAD_CREDENTIALS("E4011", "아이디 또는 비밀번호가 잘못되었습니다.", HttpStatus.UNAUTHORIZED),
    ACCOUNT_DISABLED("E4012", "계정이 비활성화되어 있습니다.", HttpStatus.UNAUTHORIZED),
    ACCOUNT_LOCKED("E4013", "계정이 잠겨 있습니다.", HttpStatus.UNAUTHORIZED),
    ACCOUNT_EXPIRED("E4014", "계정이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    CREDENTIALS_EXPIRED("E4015", "자격 증명이 만료되었습니다.", HttpStatus.UNAUTHORIZED),

    // Refresh Token Errors
    INVALID_REFRESH_TOKEN("E4020", "유효하지 않은 Refresh Token입니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_REFRESH_TOKEN("E4021", "Refresh Token이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    REVOKED_REFRESH_TOKEN("E4022", "Refresh Token이 철회되었습니다.", HttpStatus.UNAUTHORIZED),
    MISSING_REFRESH_TOKEN("E4023", "Refresh Token이 제공되지 않았습니다.", HttpStatus.BAD_REQUEST),
    MULTIPLE_REFRESH_TOKEN_USAGE("E4024", "동일한 Refresh Token이 여러 번 사용되었습니다.", HttpStatus.UNAUTHORIZED),

    // Authorization Errors
    ACCESS_DENIED("E4030", "접근이 거부되었습니다.", HttpStatus.FORBIDDEN),

    // JWT and Token Errors
    INVALID_BEARER_TOKEN("E4016", "유효하지 않은 Bearer 토큰입니다.", HttpStatus.UNAUTHORIZED),
    JWT_VALIDATION_FAILED("E4017", "JWT 검증에 실패했습니다.", HttpStatus.UNAUTHORIZED),
    INSUFFICIENT_AUTHENTICATION("E4018", "인증 정보가 부족합니다.", HttpStatus.UNAUTHORIZED),

    // Access Token Errors
    INVALID_ACCESS_TOKEN("E4050", "유효하지 않은 Access Token입니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_ACCESS_TOKEN("E4051", "Access Token이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    MISSING_ACCESS_TOKEN("E4052", "Access Token이 제공되지 않았습니다.", HttpStatus.BAD_REQUEST),

    // Resource Errors
    NO_SUCH_ELEMENT_ERROR("E4041", "요청한 리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // Server Errors
    INTERNAL_SERVER_ERROR("E5000", "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
