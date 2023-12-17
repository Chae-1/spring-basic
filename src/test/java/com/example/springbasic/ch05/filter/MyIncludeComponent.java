package com.example.springbasic.ch05.filter;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 해당 애노테이션이 부터 있다면 컴포넌트 스캔의 대상이 된다.
public @interface MyIncludeComponent {

}
