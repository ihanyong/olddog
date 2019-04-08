package com.hanyong.jianzhioffer.question3;

import java.util.ArrayList;

/**
 * Solution
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 * @author yong.han
 * 2019/4/8
 */
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (null == listNode) {
            return new ArrayList<>();
        }

        ArrayList<Integer> list = transform(listNode, 0);
        return list;
    }

    private  ArrayList<Integer> transform(ListNode listNode, int index) {
        ArrayList<Integer> list = null;
        if (listNode.next == null) {
            list = new ArrayList<>(index+1);
        } else {
            list = transform(listNode.next, index++);
        }
        list.add(listNode.val);
        return list;
    }


    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}


