package com.hanyong.jianzhioffer.question18;

import com.hanyong.jianzhioffer.TreeNode;
import com.hanyong.jianzhioffer.question4.SolutionRebuildTreeNode;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * SolutionMirroringBTTest
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionMirroringBTTest {



    @Test
    public void test() {
        SolutionRebuildTreeNode builder = new SolutionRebuildTreeNode();

        SolutionMirroringBT s = new SolutionMirroringBT();

        TreeNode root = builder.reConstructBinaryTree(null, null);
        s.Mirror(root);

        assertTrue(TreeNode.treeEq(root, builder.reConstructBinaryTree(null, null)));

        root = builder.reConstructBinaryTree(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 3, 7, 6});
        s.Mirror(root);
        assertFalse(TreeNode.treeEq(root, builder.reConstructBinaryTree(new int[]{}, new int[]{})));
        assertFalse(TreeNode.treeEq(root, builder.reConstructBinaryTree(new int[]{1}, new int[]{1})));
        assertFalse(TreeNode.treeEq(root, builder.reConstructBinaryTree(new int[]{1,2,3}, new int[]{2,1,3})));

        assertTrue(TreeNode.treeEq(root, builder.reConstructBinaryTree(new int[]{1, 3, 6, 7, 2, 5, 4}, new int[]{6, 7, 3, 1, 5, 2, 4})));


    }
}
