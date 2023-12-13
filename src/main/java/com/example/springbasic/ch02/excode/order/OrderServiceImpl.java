package com.example.springbasic.ch02.excode.order;

import com.example.springbasic.ch02.excode.discount.DiscountPolicy;
import com.example.springbasic.ch02.excode.discount.FixDiscountPolicy;
import com.example.springbasic.ch02.excode.discount.RateDiscountPolicy;
import com.example.springbasic.ch02.excode.member.Member;
import com.example.springbasic.ch02.excode.member.MemberRepository;
import com.example.springbasic.ch02.excode.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    // 관심사의 분리가 필요하다.
    // 객체가 다른 객체를 구현하는 등, 다양한 책임을 가져선 안된다.
    // 객체를 직접 생성하는 것이 아닌, 객체를 생성하는 또 다른 객체에 해당 책임을 위임
    // 애플리케이션의 전체 동작 방식을 구성하자.
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member, itemPrice);

        return new Order(member.getId(), itemName, itemPrice, discount);
    }
}
