package com.example.springbasic.ch02.mycode;

import com.example.springbasic.ch02.mycode.member.Member;
import com.example.springbasic.ch02.mycode.member.MemberType;

public class FixedDiscountPolicy implements DiscountPolicy{

    @Override
    public int discount(Member member) {
        if (member.getType() == MemberType.VIP)
            return 1000;
        return 0;
    }
}
