package com.hanyong.jianzhioffer.question15;

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
        Assert.assertEquals(null, solution.ReverseList(null));
        Assert.assertEquals("1", solution.ReverseList(ListNode.genListNode(1)).toString());
        Assert.assertEquals("1->2", solution.ReverseList(ListNode.genListNode(2)).toString());
        Assert.assertEquals("1->2->3", solution.ReverseList(ListNode.genListNode(3)).toString());
        Assert.assertEquals("1->2->3->4", solution.ReverseList(ListNode.genListNode(4)).toString());
        Assert.assertEquals("1->2->3->4->5->6->7->8->9->10", solution.ReverseList(ListNode.genListNode(10)).toString());

    }


}
