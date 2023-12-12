package com.example.springbasic.ch02.excode;

import com.example.springbasic.ch02.excode.member.Grade;
import com.example.springbasic.ch02.excode.member.Member;
import com.example.springbasic.ch02.excode.member.MemberService;
import com.example.springbasic.ch02.excode.member.MemberServiceImpl;
import com.example.springbasic.ch02.excode.order.Order;
import com.example.springbasic.ch02.excode.order.OrderService;
import com.example.springbasic.ch02.excode.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "hye", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "item1", 10000);

        System.out.println("order = " + order);
    }
}
