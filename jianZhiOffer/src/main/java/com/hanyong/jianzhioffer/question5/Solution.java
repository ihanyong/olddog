package com.hanyong.jianzhioffer.question5;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Solution
 *
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * @author yong.han
 * 2019/4/8
 */
public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    Lock lock = new ReentrantLock();
    Condition popCondition = lock.newCondition();

    public void push(int node) {
        lock.tryLock();
        try {
            stack1.push(node);
            popCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public int pop() {
        lock.tryLock();
        try {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            } else {
                if (stack1.isEmpty()) {
                    popCondition.awaitUninterruptibly();
                }
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
        } finally {
            lock.unlock();
        }
    }
}
