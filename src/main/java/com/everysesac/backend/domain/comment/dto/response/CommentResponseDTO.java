package com.everysesac.backend.domain.comment.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String commentId;
    private String name;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
