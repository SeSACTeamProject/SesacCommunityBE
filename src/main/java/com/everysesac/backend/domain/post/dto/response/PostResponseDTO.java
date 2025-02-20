package com.everysesac.backend.domain.post.dto.response;

import com.everysesac.backend.domain.post.entity.PostStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDTO {

    private Long postId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private PostStatus postStatus;

    private Integer viewsCount;

    private Integer commentsCount;

    private Integer likesCount;



    @QueryProjection
    public PostResponseDTO(Long postId, String title, LocalDateTime createdAt, PostStatus postStatus, Integer viewsCount, Integer commentsCount, Integer likesCount) {
        this.postId = postId;
        this.title = title;
        this.createdAt = createdAt;
        this.postStatus = postStatus;
        this.viewsCount = viewsCount;
        this.commentsCount = commentsCount;
        this.likesCount = likesCount;
    }

    @QueryProjection
    public PostResponseDTO(Long postId, String title, String content, LocalDateTime createdAt, PostStatus postStatus, Integer viewsCount, Integer commentsCount, Integer likesCount) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.postStatus = postStatus;
        this.viewsCount = viewsCount;
        this.commentsCount = commentsCount;
        this.likesCount = likesCount;
    }
}

