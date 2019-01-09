package com.monkey.olddog.leetcode;

import java.util.Stack;

/**
 * Q682
 *
 * @author yong.han
 * 2019/1/3
 */
public class Q682 {


    public static void main(String[] args) {

        String[] ops = new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"};
        System.out.println(new Q682().calPoints(ops));


    }


    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String op : ops) {
            if ("+".equals(op)) {
                Integer top = stack.pop();
                Integer newOp = top + stack.peek();
                stack.push(top);
                stack.push(newOp);
            } else if  ("D".equals(op)) {
                stack.push(stack.peek() * 2);
            } else if  ("C".equals(op)) {
                stack.pop();
            } else {
                stack.push(Integer.valueOf(op));
            }
        }

        int result = 0;
        for (Integer integer : stack) {
            result += integer;
        }
        return result;
    }
}
