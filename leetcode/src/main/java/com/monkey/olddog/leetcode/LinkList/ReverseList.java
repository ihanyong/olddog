package com.monkey.olddog.leetcode.LinkList;

/**
 * @author hanyong
 * @Date 2018/9/29
 */
public class ReverseList {
    public static ListNode reverseList(ListNode head) {
        if (null == head) {
            return head;
        }

        ListNode current = head;
        ListNode pre = null;
        ListNode next = null;

        while (current.next != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        current.next = pre;

        return current;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(null);
        System.out.println(head);
        System.out.println(reverseList(head));

    }

}
