package com.example.springbasic.ch03.excode;

import com.example.springbasic.ch03.excode.member.Grade;
import com.example.springbasic.ch03.excode.member.Member;
import com.example.springbasic.ch03.excode.member.MemberService;
import com.example.springbasic.ch03.excode.order.Order;
import com.example.springbasic.ch03.excode.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "hye", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "item1", 10000);

        System.out.println("order = " + order);
    }
}
