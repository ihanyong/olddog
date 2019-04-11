package com.hanyong.jianzhioffer.question21;

/**
 * SolutionIsPopOrder
 *
 *
 * 输入两个整数序列，
 * 第一个序列表示栈的压入顺序，
 * 请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 * @author yong.han
 * 2019/4/11
 */
public class SolutionIsPopOrder {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA.length != popA.length) {
            return false;
        }
        int[] stack = new int[pushA.length];

        int nextStackIndex = 0;
        int popIndex = 0;

        for (int push : pushA) {
            stack[nextStackIndex++] = push;

            while (nextStackIndex > 0 && stack[nextStackIndex - 1] == popA[popIndex]) {
                popIndex++;
                nextStackIndex--;
            }
        }

        while (nextStackIndex-- > 0) {
            if (popIndex >= popA.length || stack[nextStackIndex] != popA[popIndex]) {
                return false;
            }

            popIndex++;
        }

        return true;
    }

}
