package com.hanyong.jianzhioffer.question17;

import com.hanyong.jianzhioffer.TreeNode;

/**
 * SolutionSubTree
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionSubTree {

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        if (includesFromRoot(root1, root2)) {
            return true;
        }
        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    public boolean includesFromRoot(TreeNode r1, TreeNode r2) {
        if (r1 == r2) {
            return true;
        }
        if (r2 == null) {
            return true;
        }
        if (r1 == null) {
            return false;
        }

        if (r1.val != r2.val) {
            return false;
        } else {
            return includesFromRoot(r1.left, r2.left) && includesFromRoot(r1.right, r2.right);
        }
//        return r1.val == r2.val && treeEq(r1.left, r2.left) && treeEq(r1.right, r2.right);
    }


}
