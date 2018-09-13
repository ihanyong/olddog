package com.monkey.olddog.leetcode.isValidSudoku;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Solution
 *
 * @author yong.han
 * 2018/9/13
 */
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {

        }

        return false;
    }


    public boolean isValid(char[] chars) {
        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            if ('.' != c) {
                if (!set.contains(c)) {
                    set.add(c);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
