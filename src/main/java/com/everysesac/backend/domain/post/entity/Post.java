package com.everysesac.backend.domain.post.entity;

import com.everysesac.backend.domain.user.entity.User;
import com.everysesac.backend.global.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import static com.everysesac.backend.domain.post.entity.PostStatus.IN_PROGRESS;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA 기본 생성자
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "posts")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private PostType postType ;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PostStatus postStatus = IN_PROGRESS;

    @Builder.Default
    private Integer viewsCount = 0;

    @Builder.Default
    private Integer likesCount = 0;

    @Builder.Default
    private Integer commentsCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @Builder.Default
//    @OneToMany(mappedBy = "post") // cascade = CascadeType.ALL
//    private List<Like> likes = new ArrayList<>();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "post") // cascade = CascadeType.ALL
//    private List<Comment> comments = new ArrayList<>();

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changePostType(PostType postType) {
        this.postType = postType;
    }

    public void changePostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    public void increaseCommentsCount() {
        this.commentsCount += 1;
    }
    public void decreaseCommentsCount() {
        this.commentsCount -= 1;
    }




}
