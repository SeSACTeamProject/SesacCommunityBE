package com.everysesac.backend.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommentCreateRequestDTO {
    private Long userId;
    private Long postId;
    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;
}
