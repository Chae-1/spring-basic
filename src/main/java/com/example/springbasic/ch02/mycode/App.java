package com.example.springbasic.ch02.mycode;

import com.example.springbasic.ch02.mycode.member.Member;
import com.example.springbasic.ch02.mycode.member.MemberService;
import com.example.springbasic.ch02.mycode.member.MemberServiceImpl;

import static com.example.springbasic.ch02.mycode.member.MemberType.VIP;

public class App {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderService();
        memberService.save(new Member(1L, "Hyeongil", VIP));

        Member member = memberService.findMember(1L);

    }
}
