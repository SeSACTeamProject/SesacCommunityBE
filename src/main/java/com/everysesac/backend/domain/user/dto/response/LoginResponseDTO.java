package com.everysesac.backend.domain.user.dto.response;

import com.everysesac.backend.domain.user.entity.Role;
import com.everysesac.backend.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String email;
    private String name;
    private String token;
    private Role role;

    public static LoginResponseDTO fromUser(User user, String token) {
        return new LoginResponseDTO(user.getEmail(), user.getName(), token, user.getRole());
    }
}
