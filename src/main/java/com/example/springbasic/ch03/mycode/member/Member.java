package com.example.springbasic.ch03.mycode.member;

import lombok.Getter;

@Getter
public class Member {
    private Long id;
    private String name;
    private MemberType type;

    public Member(Long id, String name, MemberType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

}
