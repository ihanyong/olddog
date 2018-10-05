package com.monkey.olddog.leetcode.LinkList;


/**
 * @author hanyong
 * @Date 2018/9/30
 */
public class MergeTwoLists {


    public static void main(String[] args) {
        System.out.println(mergeTwoLists(ListNode.of(1, 3, 4, 5), ListNode.of(2, 4, 5, 6, 7, 8, 9)));

    }


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 先拿到一个小的节点，做为 head
        // tail 一直指向尾节点， 开始是和head指向一个节点，
        // 每次从l1和l2中取到较小的那个节点，将tail 推进到这个节点
        // 如果l1或l2任何一个为null, 说明已经消耗完一个队列，可以结束


        ListNode head = new ListNode(-1);
        ListNode tail = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = l1 == null ? l2 : l1;

        return head.next;
    }

}
