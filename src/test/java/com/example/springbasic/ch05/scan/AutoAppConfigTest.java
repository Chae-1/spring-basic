package com.example.springbasic.ch05.scan;

import com.example.springbasic.ch03.excode.member.MemberService;
import com.example.springbasic.ch03.excode.member.MemberServiceImpl;
import com.example.springbasic.ch03.excode.AutoAppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    void basicScan() {
        MemberService memberService = ac.getBean("memberServiceImpl", MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
}
