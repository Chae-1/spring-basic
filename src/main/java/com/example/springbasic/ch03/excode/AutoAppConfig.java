package com.example.springbasic.ch03.excode;

import com.example.springbasic.ch03.excode.member.MemberRepository;
import com.example.springbasic.ch03.excode.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// @Component 가 붙은 모든 클래스를 빈으로 등록한다.
public class AutoAppConfig {

}
