package com.monkey.olddog.leetcode.binary.tree;

/**
 * @author hanyong
 * @Date 2018/10/4
 */
public class IsValidBST {

    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBST(TreeNode root, long min, long max) {
//        if (root == null) {
//            return true;
//        }
//
//        return (root.left != null ? root.left.val < root.val : true)
//                && (root.right != null ? root.val < root.right.val : true)
//                && isValidBST(root.left)
//                && isValidBST(root.right);

        return root == null || (root.val < max && root.val > min && isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max));

    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);

    }
}
