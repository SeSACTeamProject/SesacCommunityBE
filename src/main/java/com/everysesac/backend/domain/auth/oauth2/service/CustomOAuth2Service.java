package com.everysesac.backend.domain.auth.oauth2.service;

import com.everysesac.backend.domain.auth.oauth2.dto.*;
import com.everysesac.backend.domain.user.entity.Role;
import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2Service extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("OAuth2 User Attributes: {}", oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;

        // Provider-specific response handling
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes()); // KakaoResponse 구현 필요
        } else {
            throw new OAuth2AuthenticationException("Unsupported provider: " + registrationId);
        }

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        User user = userRepository.findByUsername(username);

        if (user == null) {
            // Create new user
            user = createNewUser(oAuth2Response, username);
        } else {
            // Update existing user
            updateExistingUser(user, oAuth2Response);
        }

        UserDTO userDTO = UserDTO.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();

        return new CustomOAuth2User(userDTO);
    }

    private User createNewUser(OAuth2Response response, String username) {
        User user = User.builder()
                .username(username)
                .role(Role.ROLE_USER)
                .email(response.getEmail())
                .phonenumber(response.getPhonenumber())
                .name(response.getName())
                .build();
        return userRepository.save(user);
    }

    private void updateExistingUser(User user, OAuth2Response response) {
        user.changeName(response.getName());
        user.changeEmail(response.getEmail());
        user.chagnePhonenumber(response.getPhonenumber());
        userRepository.save(user);
    }
}
