package com.example.springbasic.ch03.mycode;

import com.example.springbasic.ch03.mycode.member.Member;

public interface DiscountPolicy {

    int discount(Member member);
}
