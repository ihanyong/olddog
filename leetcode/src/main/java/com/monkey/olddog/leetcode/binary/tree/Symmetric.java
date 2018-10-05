package com.monkey.olddog.leetcode.binary.tree;

/**
 * @author hanyong
 * @Date 2018/10/4
 */
public class Symmetric {
    public static boolean isSymmetric(TreeNode root) {
        return root == null ||  symmetric(root.left, root.right);
    }

    public static boolean symmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 != null && node2 != null) {
            return (node1.val == node2.val) && symmetric(node1.left, node2.right) && symmetric(node1.right, node2.left);
        } else {
            return false;
        }
    }
}
