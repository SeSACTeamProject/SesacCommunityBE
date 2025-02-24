package com.everysesac.backend.domain.auth.jwt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoinDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String campusId;
    private String curriculumID;
}
