package com.mirim.board;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 500)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",nullable = false)
    private Member autor;
    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Comment() {}

    public Comment(String content, Post post, Member autor) {
        this.content = content;
        this.post = post;
        this.autor = autor;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Post getPost() {
        return post;
    }

    public Member getAutor() {
        return autor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
