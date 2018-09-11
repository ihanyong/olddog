package com.monkey.olddog.leetcode;/**
 * Created by hanyong on 2017/7/19.
 */

/**
 * @author hanyong
 * @Date 2017/7/19
 */

import java.util.LinkedList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {

    public static void main(String[] args) {

        Integer[] n1 = {5};
        Integer[] n2 = {5};

        ListNode l1 = toListNode(n1);
        ListNode l2 = toListNode(n2);

        ListNode l3 = addTwoNumbers(l1, l2);

        Integer[] n3 = toArray(l3);


        printArray(n1);
        printArray(n2);
        printArray(n3);

    }

    private static void printArray(Object[] array) {
        if (array == null) {
            System.out.println("null");
        }
        System.out.print("[");
        boolean co = false;
        for (Object o : array) {
            if (co) {
                System.out.print(",");
            } else {
                co = true;
            }
            System.out.print(o);
        }
        System.out.println("]");
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode preNode = new ListNode(0);


        ListNode p = l1;
        ListNode q = l2;
        ListNode sp = preNode;

        int extra = 0;

        while (p != null || q != null || extra >0) {
            int sum = (p!=null?p.val:0) + (q!=null?q.val:0) + extra;

            extra = sum/10;

            sp.next = new ListNode(sum%10);
            sp=sp.next;

            p=p==null?null:p.next;
            q=q==null?null:q.next;

        }

        return preNode.next;

    }

//    private static ListNode sum(ListNode lc, ListNode rc, ListNode sc) {
//        int n1 = lc == null ? 0 : lc.val;
//        int n2 = rc == null ? 0 : rc.val;
//        int n3 = sc == null ? 0 : sc.val;
//
//        int sum = n1+n2+n3;
//
//        ListNode node = new ListNode(0);
//        ListNode c = node;
//
//        while (sum > 0) {
//            int n = sum%10;
//            sum /=10;
//
//            ListNode temp = new ListNode(n);
//
//            c.next = temp;
//            c = temp;
//
//        }
//
//        return node.next==null?node:node.next;
//
//    }


    public static Integer[] toArray(ListNode listNode){
        List<Integer> list = new LinkedList<>();
        ListNode current = listNode;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        return list.toArray(new Integer[list.size()]);
    }

    public static ListNode toListNode(Integer[] array) {

        ListNode current = null;
        for (int i = array.length - 1; i >= 0; i--) {
            ListNode node = new ListNode(array[i]);
            node.next = current;
            current = node;
        }

        return current;
    }

}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x ) { val = x;}
}

