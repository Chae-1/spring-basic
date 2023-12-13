package com.example.springbasic.ch03.mycode;

import lombok.Getter;

@Getter
public class Order {
    private long orderId;
    private int orderPrice;
    private String orderName;
    private int discountPrice;

    public Order(long orderId, int orderPrice, String orderName, int discountPrice) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.orderName = orderName;
        this.discountPrice = discountPrice;
    }

}
