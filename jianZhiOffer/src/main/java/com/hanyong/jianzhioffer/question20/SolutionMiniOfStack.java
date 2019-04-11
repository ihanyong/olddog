package com.hanyong.jianzhioffer.question20;

import java.util.Stack;

/**
 * SolutionMiniOfStack
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionMiniOfStack {

    private Stack<Integer> stack = new Stack<>();

    private Stack<Integer> miniStack = new Stack<>();

    public void push(int node) {
        if (stack.isEmpty() || node < miniStack.peek()) {
            miniStack.push(node);
        } else {
            miniStack.push(miniStack.peek());
        }
        stack.push(node);


    }

    public void pop() {
        stack.pop();
        miniStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return miniStack.peek();
    }
}
