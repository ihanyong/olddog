package com.monkey.olddog.leetcode.LinkList;


/**
 * @author hanyong
 * @Date 2018/9/29
 */
public class DeleteNode {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
