package com.example.springbasic.ch02.excode.discount;

import com.example.springbasic.ch02.excode.member.Member;

public interface DiscountPolicy {
    /**
     *
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
