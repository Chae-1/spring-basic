package com.example.springbasic.ch02.excode.discount;

import com.example.springbasic.ch02.excode.member.Grade;
import com.example.springbasic.ch02.excode.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }
        return 0;
    }
}
