package com.example.springbasic.ch03.excode.discount;

import com.example.springbasic.ch03.excode.member.Grade;
import com.example.springbasic.ch03.excode.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {
    private int discountAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountAmount;
        }
        return 0;
    }
}
