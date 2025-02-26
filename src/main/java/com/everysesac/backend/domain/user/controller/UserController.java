package com.everysesac.backend.domain.user.controller;



import com.everysesac.backend.domain.user.dto.request.UserRequestDTO;
import com.everysesac.backend.domain.user.dto.response.UserResponseDTO;
import com.everysesac.backend.domain.user.service.UserService;

import com.everysesac.backend.global.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<UserResponseDTO>> signup(@Valid @RequestBody UserRequestDTO requestDTO) {

        UserResponseDTO responseDto = userService.signup(requestDTO);
        ApiResponse<UserResponseDTO> response = ApiResponse.<UserResponseDTO>builder()
                .status("success")
                .code(201)
                .message("User registered successfully.")
                .data(responseDto)
                .build();
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody @Valid LoginRequestDTO requestDTO) {
//        LoginResponseDTO responseDTO = userService.login(requestDTO);
//        ApiResponse<LoginResponseDTO> response = ApiResponse.<LoginResponseDTO>builder()
//                .status("success")
//                .code(200)
//                .message("Login successful.")
//                .data(responseDTO)
//                .build();
//        return ResponseEntity.ok(response);
//    }
}
