package com.monkey.olddog.leetcode.str;

/**
 * @author hanyong
 * @Date 2018/9/18
 */
public class MyAtoiSolution {
    public static int myAtoi(String str) {
        char[] chs = str.trim().toCharArray();
        int f = 1;
        int r = 0;
        for (int i = 0; i < chs.length; i++) {
            if (i == 0 && '-' == chs[i]) {
                f = -1;
                continue;
            }else if (i == 0 && '+' == chs[i]) {
                continue;
            }
            if (chs[i] >= '0' && chs[i] <= '9') {
                if (r > Integer.MAX_VALUE / 10) {
                    return f > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                } else if (r == Integer.MAX_VALUE / 10 && f > 0 && chs[i] > '7') {
                    return Integer.MAX_VALUE;
                } else if (r == Integer.MAX_VALUE / 10 && f < 0 && chs[i] > '8') {
                    return Integer.MIN_VALUE;
                }


                r = r*10 + (chs[i] - '0');
            } else {
                break;
            }

        }
        return f * r;
    }

    public static void main(String[] args) {

        System.out.println(myAtoi("2147483800"));

//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//
//
//        System.out.println("--------");
//        System.out.println(myAtoi("-1"));
//        System.out.println(myAtoi("-0"));
//        System.out.println(myAtoi("0"));
//        System.out.println(myAtoi("1"));
//        System.out.println(myAtoi("10"));
//        System.out.println(myAtoi("100001"));
//        System.out.println(myAtoi("-1000001"));
//        System.out.println(myAtoi("2147483647"));
//        System.out.println(myAtoi("2147483648"));
//        System.out.println(myAtoi("-2147483648"));
//        System.out.println(myAtoi("-2147483649"));
    }
}
