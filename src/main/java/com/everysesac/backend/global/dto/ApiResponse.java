package com.everysesac.backend.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private String status;
    private int code;
    private String message;
    private Integer size;
    private List<T> dtoList;
    private T data;

    public ApiResponse(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
