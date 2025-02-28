package com.everysesac.backend.domain.comment.entity;


import com.everysesac.backend.domain.post.entity.Post;
import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.global.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA 기본 생성자
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void changeContent(String content) {
        this.content = content;
    }

}
