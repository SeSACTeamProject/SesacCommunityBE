package com.everysesac.backend.domain.auth.oauth2.service;

import com.everysesac.backend.domain.auth.oauth2.dto.NaverResponse;
import com.everysesac.backend.domain.auth.oauth2.dto.OAuth2Response;
import com.everysesac.backend.domain.auth.oauth2.dto.UserDTO;
import com.everysesac.backend.domain.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomOAuth2Service extends DefaultOAuth2UserService {

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

        UserDTO userDTO = UserDTO.builder().role("ROLE_USER").email(oAuth2Response.getEmail()).name(oAuth2Response.getName()).username(username).build();



    }
}
