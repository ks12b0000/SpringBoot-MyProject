package com.example.myproject.domain.user;

import com.example.myproject.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    // 유저 아이디
    @Column(length = 100, nullable = false, unique = true)
    private String username;

    // 유저 이름
    @Column(length = 100, nullable = false)
    private String name;

    // 유저 이메일
    @Column(length = 300, nullable = false)
    private String email;

    // 유저 비밀번호
    @Column(length = 300, nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "ROLE_USER";

    public User(String username, String name, String email, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
