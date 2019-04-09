package com.hanyong.jianzhioffer.question14;

import com.hanyong.jianzhioffer.ListNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * SolutionTest
 *
 * @author yong.han
 * 2019/4/9
 */
public class SolutionTest {
    @Test
    public void test() {
        Solution solution = new Solution();
        ListNode head = ListNode.genListNode(10);
        Assert.assertEquals(1, solution.FindKthToTail(head, 1).val);
        Assert.assertEquals(2, solution.FindKthToTail(head, 2).val);
        Assert.assertEquals(3, solution.FindKthToTail(head, 3).val);
        Assert.assertEquals(4, solution.FindKthToTail(head, 4).val);
        Assert.assertEquals(5, solution.FindKthToTail(head, 5).val);
        Assert.assertEquals(6, solution.FindKthToTail(head, 6).val);
        Assert.assertEquals(9, solution.FindKthToTail(head, 9).val);
        Assert.assertEquals(10, solution.FindKthToTail(head, 10).val);


        Assert.assertNull(solution.FindKthToTail(head, 0));
        Assert.assertNull(solution.FindKthToTail(head, 11));
        Assert.assertNull(solution.FindKthToTail(null, 1));

    }



}
