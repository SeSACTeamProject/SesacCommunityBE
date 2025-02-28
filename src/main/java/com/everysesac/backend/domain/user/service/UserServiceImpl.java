package com.everysesac.backend.domain.user.service;

import com.everysesac.backend.domain.user.entity.Role;
import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.domain.user.dto.response.LoginResponseDTO;
import com.everysesac.backend.domain.user.dto.request.LoginRequestDTO;
import com.everysesac.backend.domain.user.dto.request.UserRequestDTO;
import com.everysesac.backend.domain.user.dto.response.UserResponseDTO;
import com.everysesac.backend.domain.user.repository.UserRepository;
import com.everysesac.backend.global.util.PhonenumberUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO signup(UserRequestDTO requestDTO) {
        if(userRepository.findByUsername(requestDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("사용할 수 없는 아이디입니다.");
        }

        String lastNumber = PhonenumberUtils.extractLastFourDigits(requestDTO.getPhonenumber());

        User user = User.builder()
                .email(requestDTO.getEmail())
                .name(requestDTO.getName())
                .username(requestDTO.getUsername())
                //.password(passwordEncoder.encode(requestDTO.getPassword()))
                .password(bCryptPasswordEncoder.encode(requestDTO.getPassword()))
                .phonenumber(requestDTO.getPhonenumber())
                .lastNumber(lastNumber)
                .role(Role.ROLE_USER) //기본값 USER
                .build();

        User savedUser = userRepository.save(user);
        return new UserResponseDTO(
                savedUser.getUsername(),
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getName(),
                savedUser.getCreatedAt()
        );
    }

    @Override
    public Long findUserIdByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow().getId();
    }

//    @Override
//    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
//        User user = userRepository.findByUsername(requestDTO.getUsername())
//                .orElseThrow(() -> new IllegalArgumentException("이메일이 존재하지 않습니다."));
//
//        //if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
//        if (!requestDTO.getPassword().equals(user.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//
//        String token = "dummy-jwt-token";
//
//        return new LoginResponseDTO(
//                user.getEmail(),
//                user.getName(),
//                token,
//                user.getRole()
//        );
//    }
}

