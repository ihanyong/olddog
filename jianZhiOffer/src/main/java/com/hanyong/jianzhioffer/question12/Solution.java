package com.hanyong.jianzhioffer.question12;

/**
 * Solution
 *
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * @author yong.han
 * 2019/4/8
 */
public class Solution {

    public double Power(double base, int exponent) {
//        return Math.pow(base, exponent);
        if (Double.valueOf(base).equals(0)) {
            return 0;
        }

        if (exponent == 0) {
            return 1;
        }

        int ex = Math.abs(exponent);

        double r = 1;
        for (int i = 1; i <= ex; i++) {
            r *= base;
        }

        if (exponent < 0) {
            r = 1.0 / r;
        }
        return r;
    }
}
