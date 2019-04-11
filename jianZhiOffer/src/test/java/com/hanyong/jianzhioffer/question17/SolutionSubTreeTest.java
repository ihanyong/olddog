package com.hanyong.jianzhioffer.question17;

import com.hanyong.jianzhioffer.TreeNode;
import com.hanyong.jianzhioffer.question4.SolutionRebuildTreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * SolutionSubTreeTest
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionSubTreeTest {
    @Test
    public void test() {
        SolutionSubTree s = new SolutionSubTree();


        SolutionRebuildTreeNode rebuild = new SolutionRebuildTreeNode();
        TreeNode root = rebuild.reConstructBinaryTree(null, null);
        TreeNode sub = rebuild.reConstructBinaryTree(null, null);

        Assert.assertFalse(s.HasSubtree(root, sub));


        root = rebuild.reConstructBinaryTree(new int[]{1}, new int[]{1});
        sub = rebuild.reConstructBinaryTree(new int[]{}, new int[]{});
        Assert.assertFalse(s.HasSubtree(root, sub));

        sub = rebuild.reConstructBinaryTree(new int[]{1}, new int[]{1});
        Assert.assertTrue(s.HasSubtree(root, sub));


        root = rebuild.reConstructBinaryTree(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 3, 7, 6});
        sub = rebuild.reConstructBinaryTree(new int[]{}, new int[]{});
        Assert.assertFalse(s.HasSubtree(root, sub));

        sub = rebuild.reConstructBinaryTree(new int[]{2}, new int[]{2});
        Assert.assertTrue(s.HasSubtree(root, sub));

        sub = rebuild.reConstructBinaryTree(new int[]{4}, new int[]{4});
        Assert.assertTrue(s.HasSubtree(root, sub));

        sub = rebuild.reConstructBinaryTree(new int[]{5}, new int[]{5});
        Assert.assertTrue(s.HasSubtree(root, sub));


        sub = rebuild.reConstructBinaryTree(new int[]{2, 5}, new int[]{2, 5});
        Assert.assertTrue(s.HasSubtree(root, sub));

        sub = rebuild.reConstructBinaryTree(new int[]{1, 2, 3}, new int[]{2, 1, 2});
        Assert.assertTrue(s.HasSubtree(root, sub));

        sub = rebuild.reConstructBinaryTree(new int[]{2,4,5}, new int[]{4,2,5});
        Assert.assertTrue(s.HasSubtree(root, sub));

        sub = rebuild.reConstructBinaryTree(new int[]{2,4,5}, new int[]{5,4,2});
        Assert.assertFalse(s.HasSubtree(root, sub));

        sub = rebuild.reConstructBinaryTree(new int[]{6,7}, new int[]{6,7});
        Assert.assertFalse(s.HasSubtree(root, sub));
        sub = rebuild.reConstructBinaryTree(new int[]{6,7}, new int[]{7,6});
        Assert.assertTrue(s.HasSubtree(root, sub));

    }
}
