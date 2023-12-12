package com.example.springbasic.ch02.mycode;

import com.example.springbasic.ch02.mycode.member.Member;

public interface DiscountPolicy {

    int discount(Member member);
}
