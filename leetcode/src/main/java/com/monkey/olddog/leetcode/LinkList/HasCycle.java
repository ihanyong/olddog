package com.monkey.olddog.leetcode.LinkList;

/**
 *
 * 给定一个链表，判断链表中是否有环。
 *
 *
 * @author hanyong
 * @Date 2018/10/4
 */
public class HasCycle {

    /**
     * 这个方法被判定耗时过长， 没有通过
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode current = head;
        while (current != null) {
            if (current.next == head) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    /**
     * 使用快慢指针追赶的方式
     *
     *
     * @param head
     * @return
     */
    public static boolean hasCycle2(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }
}
