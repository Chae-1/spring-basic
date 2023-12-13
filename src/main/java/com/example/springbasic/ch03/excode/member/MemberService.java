package com.example.springbasic.ch03.excode.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
