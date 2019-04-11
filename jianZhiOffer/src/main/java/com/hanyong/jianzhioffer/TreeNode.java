package com.hanyong.jianzhioffer;

/**
 * TreeNode
 *
 * @author yong.han
 * 2019/4/11
 */
public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }


    public static boolean treeEq(TreeNode r1, TreeNode r2) {
        if (r1 == r2) {
            return true;
        }
        if (r1 == null || r2 == null) {
            return false;
        }
        return r1.val == r2.val && treeEq(r1.left, r2.left) && treeEq(r1.right, r2.right);
    }

}
