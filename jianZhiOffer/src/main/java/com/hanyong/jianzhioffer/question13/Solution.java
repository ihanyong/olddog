package com.hanyong.jianzhioffer.question13;

import java.text.MessageFormat;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Solution
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，
 * 偶数和偶数之间的相对位置不变。
 * @author yong.han
 * 2019/4/9
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] array = new int[]{1,2,3,4,5,6,7,8,9};
        System.out.println(MessageFormat.format("{0}", IntStream.of(array).mapToObj(String::valueOf).collect(Collectors.joining(",","[","]"))));
        solution.reOrderArray(array);
        System.out.println(MessageFormat.format("{0}", IntStream.of(array).mapToObj(String::valueOf).collect(Collectors.joining(",","[","]"))));


    }



    public void reOrderArray(int [] array) {
        if (null == array || array.length < 2) {
            return;
        }
        int nextOdd = 0;
        for (int lastOdd = 0; lastOdd < array.length; lastOdd++) {
            if (array[lastOdd] % 2 == 1) {
                for (int j = lastOdd; j > nextOdd; j--) {
                    array[j] += array[j - 1];
                    array[j - 1] = array[j] - array[j - 1];
                    array[j] = array[j] - array[j - 1];
                }
                nextOdd++;
            }
        }
    }
}
