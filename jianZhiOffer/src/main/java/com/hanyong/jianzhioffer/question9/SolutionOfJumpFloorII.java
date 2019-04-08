package com.hanyong.jianzhioffer.question9;

/**
 * SolutionOfJumpFloorII
 * 一只青蛙一次可以跳上1级台阶，
 * 也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * @author yong.han
 * 2019/4/8
 */
public class SolutionOfJumpFloorII {
    /**
     * f(n) = f(n-1)+f(n-2)+...+f(n-n)    (n>1)
     * f(0) = 1;
     * f(1) = 1;
     *
     */
    public int JumpFloorII(int target) {
        if (target < 2) {
            return 1;
        } else {
            return 2 << (target - 2);
        }
    }
}
