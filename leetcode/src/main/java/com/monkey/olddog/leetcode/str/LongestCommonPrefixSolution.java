package com.monkey.olddog.leetcode.str;

/**
 * LongestCommonPrefixSolution
 *
 * @author yong.han
 * 2018/9/29
 */
public class LongestCommonPrefixSolution {
    public static void main(String[] args) {
//        String[] strs = new String[]{};


        System.out.println("begin");
        System.out.println(longestCommonPrefix(null));
        System.out.println(longestCommonPrefix(new String[]{}));
        System.out.println(longestCommonPrefix(new String[]{"123456"}));
        System.out.println(longestCommonPrefix(new String[]{"123456","123456",null}));
        System.out.println(longestCommonPrefix(new String[]{"123456","123456",""}));
        System.out.println(longestCommonPrefix(new String[]{"123456","123456",}));
        System.out.println(longestCommonPrefix(new String[]{"123456","1234567",}));
        System.out.println(longestCommonPrefix(new String[]{"123adsf","123adsf7",}));
        System.out.println("end");

    }


    public static String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (null == strs[i] || strs[i].length() == 0) {
                return "";
            }
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
            }
        }

        StringBuilder pre = new StringBuilder();
        char temp;
        for (int i = 0; i < minLen; i++) {
            temp = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (temp != strs[j].charAt(i)) {
                    return pre.toString();
                }
            }
            pre.append(temp);
        }

        return pre.toString();
    }
}
