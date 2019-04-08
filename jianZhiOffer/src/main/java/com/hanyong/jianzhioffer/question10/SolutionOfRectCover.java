package com.hanyong.jianzhioffer.question10;

/**
 * SolutionOfRectCover
 *
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * @author yong.han
 * 2019/4/8
 */
public class SolutionOfRectCover {

    /**
     * 还是一个fibonacci 数列问题
     * f(n) = f(n-1) + f(n-2)   (n>1)
     * f(0) = 0
     * f(1) = 1
     *
     * @param target
     * @return
     */
    public int RectCover(int target) {
        if (target == 0) {
            return 0;
        }

        int pre1 = 1;
        int pre2 = 1;
        for (int i = 2; i <= target; i++) {
            pre1 += pre2;
            pre2 = pre1 - pre2;
        }
        return pre1;

    }
}
