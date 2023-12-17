package com.example.springbasic.ch02.excode.order;

import com.example.springbasic.ch02.excode.discount.FixDiscountPolicy;
import com.example.springbasic.ch02.excode.member.*;
import com.example.springbasic.ch02.excode.discount.RateDiscountPolicy;
import com.example.springbasic.ch02.excode.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
        OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member mem = new Member(memberId, "h", Grade.VIP);
        memberService.join(mem);

        Order order = orderService.createOrder(memberId, "item1", 10000);

        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
    }
}