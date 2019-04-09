package com.hanyong.jianzhioffer.question16;

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
        ListNode list1 = ListNode.genListNode(1, 2, 3, 4, 5);
        ListNode list2 = ListNode.genListNode(1, 2, 3, 4, 5, 6, 6, 6, 7);


        Assert.assertNull(solution.Merge(null, null));
        Assert.assertEquals("1->2->3->4->5" ,solution.Merge(list1, null).toString());
        Assert.assertEquals("1->2->3->4->5->6->6->6->7" ,solution.Merge(null, list2).toString());

        Assert.assertEquals("1->1->2->2->3->3->4->4->5->5->6->6->6->7", solution.Merge(list1, list2).toString());

    }
}
