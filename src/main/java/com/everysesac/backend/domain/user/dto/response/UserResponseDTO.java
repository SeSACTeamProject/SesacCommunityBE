package com.everysesac.backend.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String username;
    private Long userId;
    private String email;
    private String name;
    private LocalDateTime createdAt;
}

