package com.everysesac.backend.domain.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String refresh;

    private String expiration;

}
