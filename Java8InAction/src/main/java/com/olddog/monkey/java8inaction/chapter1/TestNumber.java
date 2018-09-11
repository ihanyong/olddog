package com.olddog.monkey.java8inaction.chapter1;

public class TestNumber {
    public static void main(String[] args) {
        Long v1 = Long.valueOf(10L);
        Long v3 = Long.valueOf(10L);
        long v2 = 10L;

        System.out.println(v1 == v2);
        System.out.println(v1 == v3);


    }
}
