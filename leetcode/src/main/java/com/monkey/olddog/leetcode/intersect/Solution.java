package com.monkey.olddog.leetcode.intersect;

import java.util.ArrayList;
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
