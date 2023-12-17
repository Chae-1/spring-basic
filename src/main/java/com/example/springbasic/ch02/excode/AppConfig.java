package com.example.springbasic.ch02.excode;

import com.example.springbasic.ch02.excode.discount.DiscountPolicy;
import com.example.springbasic.ch02.excode.discount.RateDiscountPolicy;
import com.example.springbasic.ch02.excode.member.MemberRepository;
import com.example.springbasic.ch02.excode.member.MemberService;
import com.example.springbasic.ch02.excode.member.MemberServiceImpl;
import com.example.springbasic.ch02.excode.member.MemoryMemberRepository;
import com.example.springbasic.ch02.excode.order.OrderService;
import com.example.springbasic.ch02.excode.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixedDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
