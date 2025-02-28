package com.everysesac.backend.domain.auth.exception;

import com.everysesac.backend.global.exception.ErrorCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class RefreshTokenException extends AuthenticationException {

    // ErrorCode 반환 메서드
    private final ErrorCode errorCode;

    // 기본 생성자
    public RefreshTokenException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // 메시지와 함께 ErrorCode를 설정하는 생성자
    public RefreshTokenException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    // 메시지, 원인(cause), ErrorCode를 설정하는 생성자
    public RefreshTokenException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
