package com.example.springbasic.ch03.excode.order;

import com.example.springbasic.ch03.excode.discount.RateDiscountPolicy;
import com.example.springbasic.ch03.excode.member.Grade;
import com.example.springbasic.ch03.excode.member.Member;
import com.example.springbasic.ch03.excode.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy());
        memberRepository.save(new Member(1L, "h", Grade.VIP));
        Order order = orderService.createOrder(1L, "item1", 10000);

        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
    }
}