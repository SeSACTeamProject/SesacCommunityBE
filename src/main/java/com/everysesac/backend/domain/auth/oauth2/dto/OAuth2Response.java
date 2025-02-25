package com.everysesac.backend.domain.auth.oauth2.dto;

public interface OAuth2Response {
    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();

    String getPhonenumber();

}
