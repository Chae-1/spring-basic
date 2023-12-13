package com.example.springbasic.ch04.singleton;

public class SingleTonService {
    private static final SingleTonService instance = new SingleTonService();

    private SingleTonService() {

    }

    public static SingleTonService getInstance() {
        return instance;
    }
}
