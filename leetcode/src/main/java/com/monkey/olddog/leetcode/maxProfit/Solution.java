package com.monkey.olddog.leetcode.maxProfit;/**
 * Created by hanyong on 2018/9/4.
 */

/**
 *
 * 下降前抛出， 上升前买入，
 *
 *
 * @author hanyong
 * @Date 2018/9/4
 */
public class Solution {

    public int maxProfit(int[] prices) {
        int buy = 1;
        int current = 0;
        int total = 0;

        for (int i = 0; i < prices.length; i++) {
            int next = 0;
            if (i < prices.length-1) {
                next = prices[i + 1];
            }

            if (buy * (next - prices[i]) > 0) {
                if (buy > 0) {
                    current = prices[i];
                } else {
                    total += prices[i] - current;
                }
                buy *= -1;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Solution s = new Solution();


        int[] prices = {7,1,5,3,6,4};
        System.out.println(s.maxProfit(prices));

    }
}
