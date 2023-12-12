package com.example.springbasic.ch02.excode.order;

import com.example.springbasic.ch02.excode.discount.DiscountPolicy;
import com.example.springbasic.ch02.excode.discount.FixDiscountPolicy;
import com.example.springbasic.ch02.excode.discount.RateDiscountPolicy;
import com.example.springbasic.ch02.excode.member.Member;
import com.example.springbasic.ch02.excode.member.MemberRepository;
import com.example.springbasic.ch02.excode.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member, itemPrice);

        return new Order(member.getId(), itemName, itemPrice, discount);
    }
}
