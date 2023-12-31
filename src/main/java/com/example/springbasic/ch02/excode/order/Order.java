package com.example.springbasic.ch02.excode.order;

import lombok.ToString;

@ToString
public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountAmount;

    public Order(Long memberId, String itemName, int itemPrice, int discountAmount) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountAmount = discountAmount;
    }

    public int calculatePrice() {
        return itemPrice - discountAmount;
    }
}
