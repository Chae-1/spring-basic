package com.example.springbasic.ch04.singleton;

import com.example.springbasic.ch03.excode.AppConfig;
import com.example.springbasic.ch03.excode.AutoAppConfig;
import com.example.springbasic.ch03.excode.member.MemberRepository;
import com.example.springbasic.ch03.excode.member.MemberServiceImpl;
import com.example.springbasic.ch03.excode.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl bean1 = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl bean2 = ac.getBean(OrderServiceImpl.class);
        MemberRepository bean = ac.getBean(MemberRepository.class);
        System.out.println("bean = " + bean);
        System.out.println("bean1 = " + bean1.getRepository());
        System.out.println("bean2 = " + bean2.getMemberRepository());
        Assertions.assertThat(bean1.getRepository())
                .isNotSameAs(bean2.getMemberRepository());
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        AutoAppConfig bean = ac.getBean(AutoAppConfig.class);
        System.out.println(bean.getClass());
    }
}
