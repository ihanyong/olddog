package com.monkey.olddog.leetcode.intersect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Solution
 *
 * @author yong.han
 * 2018/9/12
 */
public class Solution {

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);


        int[] result = new int[Math.max(nums1.length, nums2.length)];



        int i = 0;
        int index1 = 0;
        int index2 = 0;


        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] == nums2[index2]) {
                result[i++] = nums1[index1];
                index1++;
                index2++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else if (nums1[index1] < nums2[index2]) {
                index1++;
            }
        }

        return Arrays.copyOf(result, i);

    }
    public int[] intersect1(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Long> map =
                IntStream.of(nums1).mapToObj(i -> i)
                        .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        for (int n : nums2) {
            if (map.containsKey(n)) {
                list.add(n);
                long current = map.get(n);
                if (current == 1) {
                    map.remove(n);
                } else {
                    map.put(n, --current);
                }
            }
        }
        return list.stream().mapToInt(e -> e).toArray();
    }

}
