package com.monkey.olddog.leetcode.str;

/**
 * @author hanyong
 * @Date 2018/9/18
 */
public class CountAndSaySolution {

    private static String[] strs = new String[30];
    static {
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                strs[i] = "1";
            } else {
                StringBuilder sb = new StringBuilder();
                int count = 1;
                char current = strs[i - 1].charAt(0);

                for (int j = 1; j < strs[i - 1].length(); j++) {
                    if (current == strs[i - 1].charAt(j)) {
                        count++;
                    } else {
                        sb.append(count).append(current);
                        count = 1;
                        current = strs[i - 1].charAt(j);
                    }
                }
                sb.append(count).append(current);
                strs[i] = sb.toString();
            }
        }


    }


    public String countAndSay(int n) {
        return strs[n - 1];

    }


    public static String countAndSayWithNoCache(int n) {
        String answer = null;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                answer = "1";
            } else {
                StringBuilder sb = new StringBuilder();
                int count = 1;
                char current = answer.charAt(0);

                for (int j = 1; j < answer.length(); j++) {
                    if (current == answer.charAt(j)) {
                        count++;
                    } else {
                        sb.append(count).append(current);
                        count = 1;
                        current = answer.charAt(j);
                    }
                }
                sb.append(count).append(current);
                answer = sb.toString();
            }
        }

        return answer;
    }



    public static void main(String[] args) {
//        for (String str : strs) {
//            System.out.println(str);
//
//        }

        for (int i = 0; i < 5; i++) {
            System.out.println(countAndSayWithNoCache(i));
        }
    }
}
