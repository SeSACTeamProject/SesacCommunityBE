package com.everysesac.backend.domain.user.controller;


import com.everysesac.backend.domain.user.dto.request.LoginRequestDTO;
import com.everysesac.backend.domain.user.dto.response.LoginResponseDTO;
import com.everysesac.backend.domain.user.dto.request.UserRequestDTO;
import com.everysesac.backend.domain.user.dto.response.UserResponseDTO;
import com.everysesac.backend.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO responseDto = userService.signup(requestDTO);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        LoginResponseDTO responseDTO = userService.login(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
