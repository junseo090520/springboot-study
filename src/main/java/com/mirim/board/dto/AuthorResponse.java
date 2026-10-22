package com.mirim.board.dto;

import com.mirim.board.Member;

public class AuthorResponse {
    private final Long id;
    private final String name;

    private AuthorResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public static AuthorResponse from(Member member){
        return new AuthorResponse(member.getId(), member.getName());
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
