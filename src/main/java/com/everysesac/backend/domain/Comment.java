package com.everysesac.backend.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA 기본 생성자
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "comments")
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
