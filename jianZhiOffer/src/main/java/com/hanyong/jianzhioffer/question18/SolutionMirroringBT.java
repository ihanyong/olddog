package com.hanyong.jianzhioffer.question18;

import com.hanyong.jianzhioffer.TreeNode;

/**
 * SolutionMirroringBT
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionMirroringBT {
    public void Mirror(TreeNode root) {
        if (null == root || (root.left == null && root.right == null)) {
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        Mirror(root.left);
        Mirror(root.right);
    }
}
