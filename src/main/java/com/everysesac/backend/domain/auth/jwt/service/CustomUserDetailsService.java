package com.everysesac.backend.domain.auth.jwt.service;

import com.everysesac.backend.domain.auth.jwt.dto.CustomUserDetails;
import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userData = userRepository.findByUsername(username);

        if (userData.isPresent()) {
            return new CustomUserDetails(userData.orElse(null));
        }

        return null;
    }
}
