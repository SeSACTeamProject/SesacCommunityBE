package com.everysesac.backend.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class CommentRequestDTO {

    private Long commentId;

    private Long postId;

    private Long userId;

    @NotBlank(message = "댓글을 입력해 주세요.")
    private String content;

}
