package com.hanyong.jianzhioffer.question15;

import com.hanyong.jianzhioffer.ListNode;

/**
 * Solution
 *
 * 输入一个链表，反转链表后，输出新链表的表头。
 * @author yong.han
 * 2019/4/9
 */
public class Solution {

    public ListNode ReverseList(ListNode head) {
        if (null == head) {
            return null;
        }


        ListNode pre = null;
        ListNode current = head;
        ListNode next = current.next;

        while (next != null) {
            current.next = pre;

            pre = current;
            current = next;
            next = next.next ;
        }

        current.next = pre;

        return current;
    }


}
