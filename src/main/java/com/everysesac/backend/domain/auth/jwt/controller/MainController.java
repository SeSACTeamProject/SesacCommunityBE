package com.everysesac.backend.domain.auth.jwt.controller;

import com.everysesac.backend.domain.auth.jwt.dto.CustomUserDetails;
import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;

@Slf4j
@Controller
@ResponseBody
public class MainController {

    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/main")
    public String mainP() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "Main Controller" +username + role;
    }

    @GetMapping("/me")
    public String getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // CustomUserDetails에서 사용자 정보 접근
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        User byUsername = userRepository.findByUsername(username);

        log.info("{}", byUsername);

        return "Hello, " + username + "! Your role is " + role;
    }
}
