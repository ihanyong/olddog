package com.monkey.olddog.leetcode.VersionControl;

/**
 * Solution
 *
 * @author yong.han
 * 2018/12/18
 */
public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();


    }


    public int firstBadVersion(int n) {
        if (n == 1) {
            return n;
        }
        if (isBadVersion(n) && !isBadVersion(n - 1)) {
            return n;
        }

        return 0;
    }

    boolean isBadVersion(int version){
        return version > 3 ? false : true;
    }
}
