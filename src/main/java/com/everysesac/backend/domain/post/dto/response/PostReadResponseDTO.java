package com.everysesac.backend.domain.post.dto.response;

import com.everysesac.backend.domain.comment.entity.Comment;
import com.everysesac.backend.domain.post.entity.PostStatus;
import com.everysesac.backend.domain.post.entity.PostType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostReadResponseDTO {
    private Long postId;
    private String title;
    private String content;
    private PostType postType;
    private LocalDateTime createdAt;
    private PostStatus postStatus;
    private Integer viewsCount;
    private Integer commentsCount;
    private Integer likesCount;
    private List<Comment> comments;

}
