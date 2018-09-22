package com.monkey.olddog.leetcode.str.firstUniqChar;

/**
 * @author hanyong
 * @Date 2018/9/17
 */
public class Solution {

    public int firstUniqChar(String s) {
        char[] chs = s.toCharArray();

        int[] count = new int[26];

        for (char ch : chs) {
            count[ch - 97]++;
        }

        for (int i = 0; i < chs.length; i++) {
            if (count[chs[i] - 97] == 1) {
                return i;
            }
        }

        return -1;
    }

}
