package com.everysesac.backend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Validation Errors
    INVALID_REQUEST_PARAMETER("E4001", "Invalid request parameters.", HttpStatus.BAD_REQUEST),
    FIELD_VALIDATION_ERROR("E4002", "Field validation error.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("E5000", "Field error.", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_SUCH_ELEMENT_ERROR("E4041", "Requested resource not found.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
