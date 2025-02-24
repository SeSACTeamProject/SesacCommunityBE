package com.everysesac.backend.domain.user.dto.request;

import com.everysesac.backend.domain.auth.entity.SnsDiv;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String email;
    private String name;
    private String password;
    private SnsDiv snsDiv;
    private String phonenumber;
    //campusId
    //curriculumId

}