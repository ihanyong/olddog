package com.monkey.olddog.leetcode.LinkList;

/**
 * @author hanyong
 * @Date 2018/9/29
 */
public class RemoveNthFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode delNext = new ListNode(0);
        delNext.next = head;

        ListNode current = head;

        int i = 0;
        while (current.next != null) {
            if (i >= n-1) {
                delNext = delNext.next;
            }
            current = current.next;
            i++;

        }

        delNext.next = delNext.next.next;
        if (i == n - 1) {
            head = head.next;
        }

        return head;
    }

    public static void main(String[] args) {
        System.out.println(removeNthFromEnd(ListNode.of(1), 1));
        System.out.println(removeNthFromEnd(ListNode.of(2,1), 1));
        System.out.println(removeNthFromEnd(ListNode.of(2,1), 2));

        System.out.println(removeNthFromEnd(ListNode.of(6,5,4,3,2,1), 1));
        System.out.println(removeNthFromEnd(ListNode.of(6,5,4,3,2,1), 2));
        System.out.println(removeNthFromEnd(ListNode.of(6,5,4,3,2,1), 3));
        System.out.println(removeNthFromEnd(ListNode.of(6,5,4,3,2,1), 4));
        System.out.println(removeNthFromEnd(ListNode.of(6,5,4,3,2,1), 5));
        System.out.println(removeNthFromEnd(ListNode.of(6,5,4,3,2,1), 6));


    }

}
