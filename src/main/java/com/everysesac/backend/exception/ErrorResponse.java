package com.everysesac.backend.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.validation.BindingResult;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String status; // "error"
    private String code;   // e.g., "E4001"
    private String message; // e.g., "Invalid request parameters."
    private List<FieldError> errors; // 필드별 에러 목록

    public static ErrorResponse of(ErrorCode errorCode, List<FieldError> errors) {
        return new ErrorResponse("error", errorCode.getCode(), errorCode.getMessage(), errors);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private String message;

        public static List<FieldError> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(error -> new FieldError(error.getField(), error.getDefaultMessage()))
                    .toList();
        }

    }
}
