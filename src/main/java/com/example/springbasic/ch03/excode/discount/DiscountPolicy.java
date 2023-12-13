package com.example.springbasic.ch03.excode.discount;

import com.example.springbasic.ch03.excode.member.Member;

public interface DiscountPolicy {
    /**
     *
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
