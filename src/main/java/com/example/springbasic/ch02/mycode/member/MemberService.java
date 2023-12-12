package com.example.springbasic.ch02.mycode.member;

public interface MemberService {
    public void save(Member member);

    public Member findMember(Long memberId);
}
