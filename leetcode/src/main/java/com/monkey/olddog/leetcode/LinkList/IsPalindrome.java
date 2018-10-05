package com.monkey.olddog.leetcode.LinkList;

/**
 * @author hanyong
 * @Date 2018/10/4
 */
public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(ListNode.of(1,2,3,4,3,2,1)));
        System.out.println(isPalindrome(ListNode.of(1,2,2,4,3,2,1)));
        System.out.println(isPalindrome(ListNode.of(1,2,3,3,2,1)));
        System.out.println(isPalindrome(ListNode.of(1,2,2,3,2,1)));
    }


    /**
     * 请判断一个链表是否为回文链表。
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow = reverseList(slow);

        while (slow != null) {
            if (head.val != slow.val) {
                return false;
            }

            head = head.next;
            slow = slow.next;
        }

        return true;
    }

    public static ListNode reverseList(ListNode head) {
        if (null == head || head.next == null) {
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
}
