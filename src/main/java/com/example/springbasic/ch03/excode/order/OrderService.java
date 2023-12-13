package com.example.springbasic.ch03.excode.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
