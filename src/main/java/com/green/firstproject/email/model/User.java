package com.green.firstproject.email.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no") // DB의 user_no를 기본 키로 매핑
    private long userNo;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "first_login", nullable = false)
    private Boolean firstLogin;

    @Column(name = "status_message")
    private String statusMessage;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "enabled")
    private int enabled;
}