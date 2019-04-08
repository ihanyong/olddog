package com.hanyong.jianzhioffer.question3;

import com.hanyong.jianzhioffer.question3.Solution.ListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * SolutionTest
 *
 * @author yong.han
 * 2019/4/8
 */
public class SolutionTest {
    @Test
    public void test() {
        Solution solution = new Solution();

        assertNotNull(solution.printListFromTailToHead(null));
        assertEquals(0, solution.printListFromTailToHead(null).size());

        assertEquals(Collections.singletonList(1), solution.printListFromTailToHead(new ListNode(1)));


        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node4.next = node3;
        node3.next = node2;
        node2.next = node1;

        assertEquals(Arrays.asList(1,2,3,4), solution.printListFromTailToHead(node4));


    }
}
