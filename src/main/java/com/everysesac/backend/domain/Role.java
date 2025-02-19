package com.everysesac.backend.domain;

import lombok.Getter;

@Getter
public enum Role{
    ADMIN("admin"),USER("user"),PENDING("pending");

    private final String role;

    Role(String role) {
        this.role = role;
    }
}
