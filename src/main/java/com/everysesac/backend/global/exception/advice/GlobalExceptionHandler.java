package com.everysesac.backend.global.exception.advice;

import com.everysesac.backend.global.exception.ErrorCode;
import com.everysesac.backend.global.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementExceptions(Exception ex) {
        log.error("NoSuchElementException.class",ex);
        ErrorCode noSuchElementError = ErrorCode.NO_SUCH_ELEMENT_ERROR;
        ErrorResponse errorResponse = ErrorResponse.builder().code(noSuchElementError.getCode()).message(ex.getMessage()).build();
        return ResponseEntity.status(noSuchElementError.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        List<ErrorResponse.FieldError> fieldErrors = ErrorResponse.FieldError.of(bindingResult);

        ErrorResponse response = ErrorResponse.of(ErrorCode.FIELD_VALIDATION_ERROR, fieldErrors);

        return ResponseEntity.status(ErrorCode.FIELD_VALIDATION_ERROR.getStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalExceptions(Exception ex) {
        log.error("Unhandled exception occurred", ex);

        ErrorResponse response = new ErrorResponse(
                "error",
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                ex.getMessage() != null ? ex.getMessage() : ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
                null
        );

        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(response);
    }
}
