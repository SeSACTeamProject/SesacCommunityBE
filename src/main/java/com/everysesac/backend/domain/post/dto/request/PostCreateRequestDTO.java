package com.everysesac.backend.domain.post.dto.request;

import com.everysesac.backend.domain.post.entity.PostType;
import com.everysesac.backend.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCreateRequestDTO {
    private User user;
    @NotBlank(message = "title은 null값이 아니어야 합니다.")
    private String title;
    @NotBlank(message = "content는 null값이 아니어야 합니다.")
    private String content;
    @NotNull(message = "type의 타입변환에 실패하였습니다.")
    private PostType postType;
}
