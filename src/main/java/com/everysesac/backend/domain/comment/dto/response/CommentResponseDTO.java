package com.everysesac.backend.domain.comment.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDTO {
    private Long commentId;
    private String name;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @QueryProjection
    public CommentResponseDTO(Long commentId, String name, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.name = name;
        this.content = content;
        this.createdAt = createdAt;
    }
    @QueryProjection
    public CommentResponseDTO(Long commentId, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
    }

}
