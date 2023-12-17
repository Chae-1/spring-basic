package com.example.springbasic.ch04.singleton;

public class StatefulService {
//    private int price; // 상태를 유지

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price;
        return price;
    }

//    public int getPrice() {
//        return price;
//    }

}
