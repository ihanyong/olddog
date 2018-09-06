package com.monkey.olddog.leetcode.containsDuplicate;/**
 * Created by hanyong on 2018/9/5.
 */

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author hanyong
 * @Date 2018/9/5
 */
public class Solutoin {
    public boolean containsDuplicate(int[] nums) {
        return IntStream.of(nums).distinct().distinct().count() == nums.length;
    }
}
