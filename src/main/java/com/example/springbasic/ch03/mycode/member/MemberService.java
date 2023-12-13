package com.example.springbasic.ch03.mycode.member;

public interface MemberService {
    public void save(Member member);

    public Member findMember(Long memberId);
}
