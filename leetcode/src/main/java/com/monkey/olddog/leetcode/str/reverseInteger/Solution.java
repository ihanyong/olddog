package com.monkey.olddog.leetcode.str.reverseInteger;

/**
 * @author hanyong
 * @Date 2018/9/13
 */
public class Solution {
    // -100

    public static int reverse(int x) {
        int r = 0;

        while (x != 0) {
            int pop = x%10;
            x /= 10;

            if (r > Integer.MAX_VALUE / 10 || (r == Integer.MAX_VALUE && r > 7)) {
                return 0;
            }

            if (r < Integer.MIN_VALUE / 10 || (r == Integer.MIN_VALUE && r < -8)) {
                return 0;
            }

            r = r * 10 + pop;
        }

        return r;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));

    }

}
