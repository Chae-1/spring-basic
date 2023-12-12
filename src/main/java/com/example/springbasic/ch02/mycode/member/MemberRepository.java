package com.example.springbasic.ch02.mycode.member;

public interface MemberRepository {
    void save(Member member);
    Member select(Long memberId);
}
