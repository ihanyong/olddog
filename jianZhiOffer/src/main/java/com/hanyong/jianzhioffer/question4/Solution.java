package com.hanyong.jianzhioffer.question4;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * Solution
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * @author yong.han
 * 2019/4/8
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

//        int[] pre = new int[]{1,2,3};
//        int[] in = new int[]{2,1,3};
        int[] pre = new int[]{1,2,5,6,3,7,8};
        int[] in =  new int[]{5,2,6,1,3,8,7};

        TreeNode node = solution.reConstructBinaryTree(pre, in);

        System.out.println("result");

    }



    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        if (pre.length == 1) {
            return  new TreeNode(pre[0]);
        }
        // split the pre
        // split the in
        int[][] splitResult = splitPreAndIn(pre, in);
        int[] leftIn = splitResult[0];
        int[] rightIn = splitResult[1];
        int[] leftPre = splitResult[2];
        int[] rightPre = splitResult[3];

        TreeNode subRoot = new TreeNode(pre[0]);
        subRoot.left = reConstructBinaryTree(leftPre, leftIn);
        subRoot.right = reConstructBinaryTree(rightPre, rightIn);

        return subRoot;
    }

    private int[][] splitPreAndIn(int[] pre, int[] ints) {
        int root = pre[0];
        int[][] splitResult = new int[4][];

        for (int i = 0; i < ints.length; i++) {
            if (root == ints[i]) {
                splitResult[0] = Arrays.copyOfRange(ints, 0, i);
                splitResult[1] = Arrays.copyOfRange(ints, i+1, ints.length);

                splitResult[2] = Arrays.copyOfRange(pre, 1, i+1);
                splitResult[3] = Arrays.copyOfRange(pre, i+1, pre.length);



                return splitResult;
            }
        }
        throw new IllegalArgumentException(MessageFormat.format("error input, root:{0}, in[]: {1}", root, ints));
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
