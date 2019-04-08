package com.hanyong.jianzhioffer.question7;

/**
 * SolutionOfFibonacci
 * 大家都知道斐波那契数列，现在要求输入一个整数n，
 * 请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 *
 * n<=39
 * @author yong.han
 * 2019/4/8
 */
public class SolutionOfFibonacci {
    private static int[] fibo = new int[40];
    static {
        fibo[0] = 0;fibo[1] = 1;
        for (int i = 2; i < fibo.length; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }
    }
    public int Fibonacci(int n) {
        return fibo[n];
    }
}
