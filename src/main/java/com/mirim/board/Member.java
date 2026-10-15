package com.mirim.board;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="login_id",nullable = false, unique = true, length = 50)
    private String loginId;
    @Column(nullable = false,length = 100)
    private String password;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    protected Member() {
    }

    public Member(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
