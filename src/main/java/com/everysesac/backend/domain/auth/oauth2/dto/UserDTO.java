package com.everysesac.backend.domain.auth.oauth2.dto;

import com.everysesac.backend.domain.user.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String username;

    private String email;

    private String name;

    private String role;


}
