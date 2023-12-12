package com.example.springbasic.ch02.excode.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);

}
