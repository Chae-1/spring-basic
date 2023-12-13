package com.example.springbasic.ch03.mycode.member;

public interface MemberRepository {
    void save(Member member);
    Member select(Long memberId);
}
