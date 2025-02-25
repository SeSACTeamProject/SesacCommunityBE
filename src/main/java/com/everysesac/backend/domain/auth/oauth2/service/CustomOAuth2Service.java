package com.everysesac.backend.domain.auth.oauth2.service;

import com.everysesac.backend.domain.auth.oauth2.dto.CustomOAuth2User;
import com.everysesac.backend.domain.auth.oauth2.dto.NaverResponse;
import com.everysesac.backend.domain.auth.oauth2.dto.OAuth2Response;
import com.everysesac.backend.domain.auth.oauth2.dto.UserDTO;
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
        log.info("{}", oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        User existData = userRepository.findByUsername(username);

        if (existData == null) {
            User user = User.builder()
                    .username(username)
                    .role(Role.ROLE_USER)
                    .email(oAuth2Response.getEmail())
                    .phonenumber(oAuth2Response.getPhonenumber())
                    .name(oAuth2Response.getName())
                    .build();
            userRepository.save(user);

            UserDTO userDTO = UserDTO.builder()
                    .username(username)
                    .name(oAuth2Response.getName())
                    .phonenumber(oAuth2Response.getPhonenumber())
                    .email(oAuth2Response.getEmail())
                    .role("ROLE_USER")
                    .build();
            return new CustomOAuth2User(userDTO);
        } else {
            existData.changeName(oAuth2Response.getName());
            existData.changeEmail(oAuth2Response.getEmail());
            existData.chagnePhonenumber(oAuth2Response.getPhonenumber());
            userRepository.save(existData);
            UserDTO userDTO = UserDTO.builder()
                    .username(username)
                    .name(existData.getName())
                    .phonenumber(existData.getPhonenumber())
                    .email(existData.getEmail())
                    .role(existData.getRole().toString())
                    .build();
            return new CustomOAuth2User(userDTO);
        }


    }
}
