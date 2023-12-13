package com.example.springbasic.ch03.excode.order;

import com.example.springbasic.ch03.excode.discount.DiscountPolicy;
import com.example.springbasic.ch03.excode.member.Member;
import com.example.springbasic.ch03.excode.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member, itemPrice);

        return new Order(member.getId(), itemName, itemPrice, discount);
    }
}
