package com.monkey.olddog.leetcode.str;

import java.util.Arrays;

/**
 * @author hanyong
 * @Date 2018/9/17
 */
public class IsAnagramSolution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();

        Arrays.sort(sa);
        Arrays.sort(ta);

        return Arrays.equals(sa, ta);

    }
}
