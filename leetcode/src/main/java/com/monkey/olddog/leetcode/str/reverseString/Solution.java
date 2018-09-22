package com.monkey.olddog.leetcode.str.reverseString;

/**
 * Solution
 * 编写一个函数，其作用是将输入的字符串反转过来。
 * @author yong.han
 * 2018/9/13
 */
public class Solution {

    public String reverseString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
                char t = chars[i];
                chars[i] = chars[chars.length - 1 - i];
                chars[chars.length - 1 - i] = t;
        }
        return String.valueOf(chars);
    }

}
