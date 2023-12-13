package com.example.springbasic.ch04.singleton;

import com.example.springbasic.ch03.excode.AppConfig;
import com.example.springbasic.ch03.excode.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 1. 호출할 때 마다 객체를 생성한다.
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingleTonService instance1 = SingleTonService.getInstance();
        SingleTonService instance2 = SingleTonService.getInstance();
        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        // sameAs -> 인스턴스가 같은지 비교. -> 동일성을 비교
        // EqualTo -> 인스턴스간 데이터가 같은지 비교 -> 동등성을 비교
        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너의 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 1. 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean(MemberService.class);
        // 1. 호출할 때 마다 객체를 생성한다.
        MemberService memberService2 = ac.getBean(MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isEqualTo(memberService2);

    }
}
