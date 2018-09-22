package com.monkey.olddog.leetcode.str;

/**
 * @author hanyong
 * @Date 2018/9/18
 */
public class strStrSolution {

    public static int strStr(String haystack, String needle) {

        if (needle.length() == 0) {
            return 0;
        } else if (needle.length() > haystack.length()) {
            return -1;
        }


        char[] hs = haystack.toCharArray();
        char[] ns = needle.toCharArray();

        int len = ns.length;

        for (int i = 0; i < hs.length - len +1; i++) {
            for (int j = i; j < i+len ; j++) {
                if (hs[j] != ns[j-i]) {
                    break;
                }
                if (j == i + len - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        System.out.println(strStr("", ""));
//        System.out.println(strStr("a", "aa"));
        System.out.println(strStr("hanyong", "yong"));
        System.out.println(strStr("hanyong", "q"));

    }
}
