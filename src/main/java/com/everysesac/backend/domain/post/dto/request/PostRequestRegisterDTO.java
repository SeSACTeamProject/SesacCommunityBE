package com.everysesac.backend.domain.post.dto.request;

import com.everysesac.backend.domain.post.entity.PostStatus;
import com.everysesac.backend.domain.post.entity.PostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class PostRequestRegisterDTO {

    private Long postId;

    @NotBlank(message = "제목은 필수 입력 항목입니다")
    @Size(min = 2, max = 100, message = "제목은 2~100자 이내로 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    @Size(min = 2, max = 2000, message = "내용은 10~2000자 이내로 입력해주세요")
    private String content;

    @NotNull(message = "게시글 유형을 선택해주세요")
    private PostType postType;

    @Builder.Default
    private PostStatus postStatus = PostStatus.IN_PROGRESS;

}