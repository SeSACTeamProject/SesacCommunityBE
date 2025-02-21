package com.everysesac.backend.domain.post.dto.response;


import com.everysesac.backend.domain.comment.dto.request.CommentRequestDTO;
import com.everysesac.backend.domain.comment.dto.response.CommentResponseDTO;
import com.everysesac.backend.domain.post.entity.PostStatus;
import com.everysesac.backend.domain.post.entity.PostType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PostResponseDTO {

    private Long postId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private PostStatus postStatus;

    private PostType postType;

    private Integer viewsCount;

    private Integer commentsCount;

    private Integer likesCount;

    private List<CommentResponseDTO> comments;



    @QueryProjection
    public PostResponseDTO(Long postId, String title, LocalDateTime createdAt, PostStatus postStatus, Integer viewsCount, Integer commentsCount, Integer likesCount,PostType postType) {
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
    @QueryProjection
    public PostResponseDTO(Long postId, String title, String content, LocalDateTime createdAt, PostStatus postStatus, PostType postType, Integer viewsCount, Integer commentsCount, Integer likesCount, List<CommentResponseDTO> comments) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.postStatus = postStatus;
        this.postType = postType;
        this.viewsCount = viewsCount;
        this.commentsCount = commentsCount;
        this.likesCount = likesCount;
        this.comments = comments;
    }

}

