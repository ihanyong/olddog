package com.hanyong.jianzhioffer.question8;

/**
 * SolutionJumpFloor
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * @author yong.han
 * 2019/4/8
 */
public class SolutionJumpFloor {
    /**
     * 递归的考虑， 跳到第N级只有两种方式， 从N-2级跳2级；从N-1级跳1级。
     * 这样就是一个Fibonacci 数列（0->1; 1->1; 2->2; 3->3; ）
     *
     * f(n) = f(n-1)+f(n-2)
     */

    public int JumpFloor(int target) {
        int pre1 = 1;
        int pre2 = 1;
        for (int i = 2; i <= target; i++) {
            pre1 += pre2;
            pre2 = pre1 - pre2;
        }
        return pre1;
    }

}
