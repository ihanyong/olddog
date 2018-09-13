package com.monkey.olddog.leetcode.plusOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author hanyong
 * @Date 2018/9/12
 */
public class Solution {

    public static void main(String[] args) {
//        int[] input = new int[]{7, 2, 8, 5, 0, 9, 1, 2, 9, 5, 3, 6, 6, 7, 3, 2, 8, 4, 3, 7, 9, 5, 7, 7, 4, 7, 4, 9, 4, 7, 0, 1, 1, 1, 7, 4, 0, 0, 6};
        int[] input = new int[]{998};
        int[] out = plusOne(input);

        System.out.println(toStr(input));
        System.out.println(toStr(out));

    }

    private static String toStr(int[] arr) {
        StringBuilder builder = new StringBuilder("[");
        if (null != arr && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                builder.append(arr[i]);
                if (i < arr.length - 1) {
                    builder.append(", ");
                }
            }
        }

        return builder.append("]").toString();
    }
    /**
     * 转成数值，再转回来,  但受long 最大值的限制
     * @param digits
     * @return
     */
    public static int[] plusOne1(int[] digits) {
        long val = 0;
        List<Integer> list = IntStream.of(digits).mapToObj(d->d).collect(Collectors.toList());
        Collections.reverse(list);

        for (int i = 0; i < list.size(); i++) {
            val += ((long) Math.pow(10, i)) * list.get(i);
        }

        char[] chars = String.valueOf(val+1).toCharArray();

        int[] result = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            result[i] = chars[1] - 48;
        }

        return result;
    }

    /**
     * 递归相加
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        int[] r = Arrays.copyOf(digits, digits.length);

        for (int i = digits.length - 1; i >= 0; i--) {
            int temp = digits[i] + 1;
            if (temp == 10) {
                r[i] = 0;
                if (i == 0) {
                    int[] r1 = new int[r.length + 1];
                    r1[0] = 1;
                    System.arraycopy(r, 0, r1, 1, r.length);
                    r = r1;
                }
            } else {
                r[i] = temp;
                break;
            }
        }
        return r;
    }

}
