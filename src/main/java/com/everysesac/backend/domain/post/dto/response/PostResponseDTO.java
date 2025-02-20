package com.everysesac.backend.domain.post.dto.response;

import com.everysesac.backend.domain.post.entity.PostStatus;
import com.everysesac.backend.domain.post.entity.PostType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDTO {

    private Long postId;

    private String title;

    private String content;

    private PostType postType;

    private LocalDateTime createdAt;

    private PostStatus postStatus;

    private Integer viewsCount;

    private Integer commentsCount;

    private Integer likesCount;



    @QueryProjection
    public PostResponseDTO(Long postId, String title, LocalDateTime createdAt, PostStatus postStatus, Integer viewsCount, Integer commentsCount, Integer likesCount, PostType postType) {
        this.postId = postId;
        this.title = title;
        this.createdAt = createdAt;
        this.postStatus = postStatus;
        this.viewsCount = viewsCount;
        this.commentsCount = commentsCount;
        this.likesCount = likesCount;
        this.postType = postType;
    }

    @QueryProjection
    public PostResponseDTO(Long postId, String title, String content, LocalDateTime createdAt, PostStatus postStatus, Integer viewsCount, Integer commentsCount, Integer likesCount,PostType postType) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.postStatus = postStatus;
        this.viewsCount = viewsCount;
        this.commentsCount = commentsCount;
        this.likesCount = likesCount;
        this.postType = postType;
    }
}

