package com.monkey.olddog.leetcode.binary.tree;

/**
 * @author hanyong
 * @Date 2018/10/4
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left) + 1;
        int right = maxDepth(root.right) + 1;

        return left > right ? left : right;
    }
}
