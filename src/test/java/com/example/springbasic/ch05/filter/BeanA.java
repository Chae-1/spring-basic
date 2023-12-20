package com.example.springbasic.ch05.filter;

import org.springframework.beans.factory.annotation.Autowired;

@MyIncludeComponent
public class BeanA {

    @Autowired
    BeanC beanc;
}
