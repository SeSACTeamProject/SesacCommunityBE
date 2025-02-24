package com.everysesac.backend.domain.auth.jwt.service;

import com.everysesac.backend.domain.auth.jwt.dto.JoinDTO;
import com.everysesac.backend.domain.user.entity.Role;
import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();
        String phoneNumber = joinDTO.getPhoneNumber();

        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            return;
        }

        User data = User.builder()
                .password(bCryptPasswordEncoder.encode(password))
                .username(username)
                .role(Role.ROLE_USER)
                .email(email)
                .phonenumber(phoneNumber)
                .build();

        userRepository.save(data);
    }
}