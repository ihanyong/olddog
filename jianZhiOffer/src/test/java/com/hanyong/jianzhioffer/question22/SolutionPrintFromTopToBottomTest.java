package com.hanyong.jianzhioffer.question22;

import com.hanyong.jianzhioffer.TreeNode;
import com.hanyong.jianzhioffer.question4.SolutionRebuildTreeNode;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * SolutionPrintFromTopToBottomTest
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionPrintFromTopToBottomTest {
    @Test
    public void test() {
        SolutionPrintFromTopToBottom s = new SolutionPrintFromTopToBottom();


        SolutionRebuildTreeNode rebuild = new SolutionRebuildTreeNode();
        TreeNode root = null;
        assertNotNull(s.PrintFromTopToBottom(root));
        Assert.assertEquals(0, s.PrintFromTopToBottom(root).size());

        root = rebuild.reConstructBinaryTree(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 3, 7, 6});


        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7}, s.PrintFromTopToBottom(root).toArray());
    }
}
