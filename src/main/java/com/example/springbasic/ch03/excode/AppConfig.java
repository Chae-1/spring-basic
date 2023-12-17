package com.example.springbasic.ch03.excode;

import com.example.springbasic.ch03.excode.discount.DiscountPolicy;
import com.example.springbasic.ch03.excode.discount.RateDiscountPolicy;
import com.example.springbasic.ch03.excode.member.MemberRepository;
import com.example.springbasic.ch03.excode.member.MemberService;
import com.example.springbasic.ch03.excode.member.MemberServiceImpl;
import com.example.springbasic.ch03.excode.member.MemoryMemberRepository;
import com.example.springbasic.ch03.excode.order.OrderService;
import com.example.springbasic.ch03.excode.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class AppConfig {

        @Bean
        public MemberRepository memberRepository() {
            System.out.println("call AppConfig.memberRepository");
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberService memberService() {
            System.out.println("call AppConfig.memberService");
            return new MemberServiceImpl(memberRepository());
        }

        @Bean
        public DiscountPolicy discountPolicy() {
            System.out.println("call AppConfig.discountPolicy");
            return new RateDiscountPolicy();
        }

        @Bean
        public OrderService orderService() {
            System.out.println("call AppConfig.orderService");
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }
}
