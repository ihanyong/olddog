package com.hanyong.jianzhioffer;

/**
 * ListNode
 *
 * @author yong.han
 * 2019/4/9
 */
public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val + (null == next ? "" : "->" + next);
    }


    public static ListNode genListNode(int... nodes) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode tail = head;

        for (int node : nodes) {
            tail.next = new ListNode(node);
            tail = tail.next;
        }
        return head.next;
    }

    public static ListNode genListNode(int num) {
        ListNode next = null;
        for (int i = 1; i <= num; i++) {
            ListNode c = new ListNode(i);
            c.next = next;
            next = c;
        }
        return next;
    }
}
