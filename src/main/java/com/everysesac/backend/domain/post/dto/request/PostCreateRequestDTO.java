package com.everysesac.backend.domain.post.dto.request;

import com.everysesac.backend.domain.post.entity.PostStatus;
import com.everysesac.backend.domain.post.entity.PostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestDTO {
    @NotBlank(message = "title은 null값이 아니어야 합니다.")
    private String title;
    @NotBlank(message = "content는 null값이 아니어야 합니다.")
    private String content;
    @NotNull(message = "type의 타입변환에 실패하였습니다.")
    private PostType postType;
}
