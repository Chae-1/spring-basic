package com.example.springbasic.ch03.excode;

import com.example.springbasic.ch03.excode.member.Grade;
import com.example.springbasic.ch03.excode.member.Member;
import com.example.springbasic.ch03.excode.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 기본적으로 Config Class의 이름으로 등록이 된다.
        // Method
        MemberService service = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "member1", Grade.VIP);
        service.join(member);

        Member member1 = service.findMember(member.getId());
        System.out.println(member == member1);

    }
}
