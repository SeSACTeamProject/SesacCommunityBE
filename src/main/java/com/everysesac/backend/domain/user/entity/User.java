package com.everysesac.backend.domain.user.entity;


import com.everysesac.backend.domain.auth.jwt.entity.SnsDiv;
import com.everysesac.backend.global.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA 기본 생성자
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "users")
@ToString
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    //@Column(nullable = false)
    private String email;

    //@Column(nullable = false)
    private String username;

    //@Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "sns_div")
    private SnsDiv snsDiv;

    //@Column(nullable = false)
    private String phonenumber;

//    @Column(nullable = false)
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
