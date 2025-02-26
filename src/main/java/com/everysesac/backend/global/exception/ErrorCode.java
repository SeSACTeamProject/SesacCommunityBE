package com.everysesac.backend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Validation Errors
    INVALID_REQUEST_PARAMETER("E4001", "Invalid request parameters.", HttpStatus.BAD_REQUEST),
    FIELD_VALIDATION_ERROR("E4002", "Field validation error.", HttpStatus.BAD_REQUEST),
    ILLEGAL_ARGUMENT_ERROR("E4003", "Illegal argument provided.", HttpStatus.BAD_REQUEST),

    // Authentication Errors
    AUTHENTICATION_FAILED("E4010", "Authentication failed.", HttpStatus.UNAUTHORIZED),
    BAD_CREDENTIALS("E4011", "Invalid username or password.", HttpStatus.UNAUTHORIZED),
    ACCOUNT_DISABLED("E4012", "Account is disabled.", HttpStatus.UNAUTHORIZED),
    ACCOUNT_LOCKED("E4013", "Account is locked.", HttpStatus.UNAUTHORIZED),
    ACCOUNT_EXPIRED("E4014", "Account has expired.", HttpStatus.UNAUTHORIZED),
    CREDENTIALS_EXPIRED("E4015", "Credentials have expired.", HttpStatus.UNAUTHORIZED),

    // Authorization Errors
    ACCESS_DENIED("E4030", "Access is denied.", HttpStatus.FORBIDDEN),

    // JWT and Token Errors
    INVALID_BEARER_TOKEN("E4016", "Invalid bearer token.", HttpStatus.UNAUTHORIZED),
    JWT_VALIDATION_FAILED("E4017", "JWT validation failed.", HttpStatus.UNAUTHORIZED),
    INSUFFICIENT_AUTHENTICATION("E4018", "Insufficient authentication details provided.", HttpStatus.UNAUTHORIZED),

    // Resource Errors
    NO_SUCH_ELEMENT_ERROR("E4041", "Requested resource not found.", HttpStatus.NOT_FOUND),

    // Server Errors
    INTERNAL_SERVER_ERROR("E5000", "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
