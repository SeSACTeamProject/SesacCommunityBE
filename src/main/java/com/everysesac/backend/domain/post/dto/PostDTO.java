package com.everysesac.backend.domain.post.dto;

import com.everysesac.backend.domain.post.entity.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long bno;
    private String title;
    private String content;
    private PostType postType;
//    private String wrtier;
//    private LocalDateTime regDate;
//    private LocalDateTime modDate;
}
