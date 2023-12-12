package com.example.springbasic.ch02.mycode;

import com.example.springbasic.ch02.mycode.member.Member;
import com.example.springbasic.ch02.mycode.member.MemberRepository;
import com.example.springbasic.ch02.mycode.member.MemoryMemberRepository;

public class OrderService {
    private MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy = new FixedDiscountPolicy();

    private static Long GENERATE_ID = 1L;

    public Order createOrder(int orderPrice, String orderName, Long memberId) {
        Member findMember = memberRepository.select(memberId);
        int discount = discountPolicy.discount(findMember);
        Order order = new Order(GENERATE_ID++, orderPrice, orderName, discount);
        return order;
    }
}
