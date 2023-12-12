package com.example.springbasic.ch02.excode;

import com.example.springbasic.ch02.excode.member.Grade;
import com.example.springbasic.ch02.excode.member.Member;
import com.example.springbasic.ch02.excode.member.MemberService;
import com.example.springbasic.ch02.excode.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService service = new MemberServiceImpl();
        Member member = new Member(1L, "member1", Grade.VIP);
        service.join(member);

        Member member1 = service.findMember(member.getId());
        System.out.println(member == member1);

    }
}
