package com.monkey.olddog.leetcode.removeDuplicates;/**
 * Created by hanyong on 2018/9/3.
 */

/**
 * @author hanyong
 * @Date 2018/9/3
 */
public class Solution {


    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 1;
        while (j< nums.length) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
            j++;
        }

        return i+1;
    }


    public static void main(String[] args) {
        Solution s = new Solution();

        int[] nums = {1,2,3,4,5};
        int c = s.removeDuplicates(nums);

        for (int i = 0; i < c; i++) {
            System.out.println(nums[i]);
        }
    }
}
