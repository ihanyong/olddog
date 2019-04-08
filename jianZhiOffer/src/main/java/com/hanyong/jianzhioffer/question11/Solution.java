package com.hanyong.jianzhioffer.question11;

/**
 * Solution
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * @author yong.han
 * 2019/4/8
 */
public class Solution {

    int[] numOf1 = new int[]{0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};

    public int NumberOf1(int n) {
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            sum += numOf1[(n >>> (i*4))&0x000f];
        }
        return sum;
    }
}
