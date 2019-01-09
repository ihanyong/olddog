package com.monkey.olddog.leetcode;

import com.google.common.primitives.Ints;

import java.util.Arrays;

/**
 * Q238
 *
 * @author yong.han
 * 2019/1/3
 */
public class Q238 {

    public static void main(String[] args) {

        System.out.println(Ints.join(",", new Q238().productExceptSelf0(new int[]{1, 2, 3, 4})));
        System.out.println(Ints.join(",", new Q238().productExceptSelf2(new int[]{1, 2, 3, 4})));

    }


    public int[] productExceptSelf0(int[] nums) {





        return null;
    }
    public int[] productExceptSelf2(int[] nums) {
        long total = Arrays.stream(nums).reduce(1, (x, y) -> x * y);

        int[] ans = new int[nums.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (total / nums[i]);
        }
        return ans;
    }

}
