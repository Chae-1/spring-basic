package com.example.springbasic.ch02.excode.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
