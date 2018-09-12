package com.monkey.olddog.leetcode.singleNumber;/**
 * Created by hanyong on 2018/9/6.
 */

/**
 * @author hanyong
 * @Date 2018/9/6
 */
public class Solution {
    public int singleNumber(int[] nums) {
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            r ^= nums[i];
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(5 ^ 5 ^ 3 ^ 3);
        System.out.println(5 ^ 0);
        System.out.println(5 ^ 1);
        System.out.println(5 ^ 2);
        System.out.println(5 ^ 3);
        System.out.println(5 ^ 4);
    }

}
