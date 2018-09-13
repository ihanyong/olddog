package com.monkey.olddog.leetcode.twoSum;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution
 *
 * @author yong.han
 * 2018/9/13
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(i)) {
                return new int[]{map.get(i), i};
            } else {
                map.put(target - nums[i], i);
            }
        }
        return null;
    }

    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length-1; i++) {
            int sub = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (sub == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
