package com.monkey.olddog.leetcode.moveZeroes;

/**
 * Solution
 *
 * @author yong.han
 * 2018/9/13
 */
public class Solution {

    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && index < i) {
                nums[index++] = nums[i];
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}
