package com.everysesac.backend.domain.user.dto.request;

import com.everysesac.backend.domain.auth.entity.SnsDiv;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    private SnsDiv snsDiv;

    @NotBlank(message = "전화번호는 필수입니다.")
    private String phonenumber;
    //campusId
    //curriculumId

}