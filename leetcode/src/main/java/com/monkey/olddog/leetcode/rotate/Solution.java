package com.monkey.olddog.leetcode.rotate;/**
 * Created by hanyong on 2018/9/5.
 */

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * @author hanyong
 * @Date 2018/9/5
 */
public class Solution {
    public static void rotate(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k % nums.length == 0) {
            return;
        }
        int len = nums.length;

        int count = k % len;
        int temp = 0;
        for (int i = 0; i < count; i++) {
            temp = nums[len-1];
            for (int j = len -1 ; j >0 ; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        print(nums);
        rotate(nums, 13);
        print(nums);


    }

    private static void print(int[] args) {
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i] + ",");
            }
            System.out.println();
        }
    }
}
