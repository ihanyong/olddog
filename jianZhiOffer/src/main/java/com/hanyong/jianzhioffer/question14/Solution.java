package com.hanyong.jianzhioffer.question14;

import com.hanyong.jianzhioffer.ListNode;

/**
 * Solution
 * 输入一个链表，输出该链表中倒数第k个结点。
 * @author yong.han
 * 2019/4/9
 */
public class Solution {



    public ListNode FindKthToTail(ListNode head, int k) {
        if (null == head || k == 0) {
            return null;
        }

        ListNode target = head;
        int count = 1;
        while (head.next != null) {
            if (count >= k) {
                target = target.next;
            }

            head = head.next;
            count++;
        }
        return count >= k ? target : null;
    }

}
