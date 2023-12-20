package com.example.springbasic.autowired;

import com.example.springbasic.ch03.excode.AutoAppConfig;
import com.example.springbasic.ch03.excode.discount.DiscountPolicy;
import com.example.springbasic.ch03.excode.member.Grade;
import com.example.springbasic.ch03.excode.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class, AutoAppConfig.class);

        DiscountService service = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "user", Grade.VIP);
        int discountPrice = service.discount(member, 10000, "fixDiscountPolicy");
        assertThat(discountPrice).isEqualTo(1000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> polices;

        DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> polices) {
            this.policyMap = policyMap;
            this.polices = polices;
            System.out.println("polices = " + polices);
            System.out.println("policyMap = " + policyMap);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
