package com.everysesac.backend.domain.user.entity;


import com.everysesac.backend.domain.auth.entity.SnsDiv;
import com.everysesac.backend.global.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA 기본 생성자
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username; // 유저의 id

    @Column(nullable = false)
    private String email; // 유저의 이메일

    @Column(nullable = false)
    private String name; // 유저의 본명 -> ex)홍길동

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "sns_div")
    private SnsDiv snsDiv;

    //@Column(nullable = false)
    private String phonenumber;

    //@Column(name = "last_number", nullable = false)
//    @Column(nullable = false)
    @Column(name = "last_number")
    private String lastNumber;

//    @Builder.Default
//    @OneToMany(mappedBy = "user")
//    private List<UserCampus> userCampuses = new ArrayList<>();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "user")
//    private List<Like> likes = new ArrayList<>();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "user")
//    private List<Post> post = new ArrayList<>();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "user")
//    private List<Comment> comments = new ArrayList<>();

}
