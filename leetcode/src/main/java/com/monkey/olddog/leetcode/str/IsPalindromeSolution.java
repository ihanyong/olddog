package com.monkey.olddog.leetcode.str;

/**
 * @author hanyong
 * @Date 2018/9/17
 */
public class IsPalindromeSolution {
    public static boolean isPalindrome(String s) {

        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if ((chars[i] >= 48 && chars[i] <= 57)) {
                sb.append(chars[i]);
            } else if ((chars[i] >= 65 && chars[i] <= 90)) {
                sb.append(chars[i]);
            } else if ((chars[i] >= 97 && chars[i] <= 122)) {
                sb.append((char)(chars[i]-32));
            }
        }

        chars = sb.toString().toCharArray();


        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("0P"));
    }

}
