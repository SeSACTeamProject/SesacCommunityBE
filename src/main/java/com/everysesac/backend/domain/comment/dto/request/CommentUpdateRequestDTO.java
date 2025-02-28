package com.everysesac.backend.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommentUpdateRequestDTO {
    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;
    private Long commentId;

    private Long postId;
}
