package com.everysesac.backend.domain.user.service;

import com.everysesac.backend.domain.user.dto.request.LoginRequestDTO;
import com.everysesac.backend.domain.user.dto.response.LoginResponseDTO;
import com.everysesac.backend.domain.user.dto.request.UserRequestDTO;
import com.everysesac.backend.domain.user.dto.response.UserResponseDTO;
import com.everysesac.backend.domain.user.entity.User;

public interface UserService {
    UserResponseDTO signup(UserRequestDTO requestDTO);

    //LoginResponseDTO login(LoginRequestDTO requestDTO);

    User findUserByUsername(String username);

    Long findUserIdByUsername(String username);
}
