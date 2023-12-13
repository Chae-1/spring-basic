package com.example.springbasic.ch03.excode.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);

}
