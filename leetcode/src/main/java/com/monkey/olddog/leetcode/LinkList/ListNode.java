package com.monkey.olddog.leetcode.LinkList;

/**
 * @author hanyong
 * @Date 2018/9/29
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append(val);
        if (next != null) {
            sb.append("->").append(next.toString());
        }
        return sb.toString();
    }

    public static ListNode of (int... vals) {
        if (null == vals || vals.length == 0) {
            return null;
        }

        ListNode head = new ListNode(vals[0]);
        ListNode tail = head;

        for (int i = 1; i < vals.length; i++) {
            tail.next = new ListNode(vals[i]);
            tail = tail.next;
        }
        return head;
    }
}
