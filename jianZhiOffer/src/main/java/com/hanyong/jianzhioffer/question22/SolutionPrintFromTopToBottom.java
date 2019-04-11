package com.hanyong.jianzhioffer.question22;

import com.hanyong.jianzhioffer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * SolutionPrintFromTopToBottom
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionPrintFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        ArrayList <Integer>list=new ArrayList<Integer>();
        if(root==null)return list;
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode t=queue.poll();
            list.add(t.val);
            if(t.left!=null){
                queue.offer(t.left);
            }
            if(t.right!=null){
                queue.offer(t.right);
            }
        }
        return list;
    }
    public ArrayList<Integer> PrintFromTopToBottom_2(TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        level(0, root, list);

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.addAll(list.get(i));
        }
        return result;
    }


    private void level(int level, TreeNode root, ArrayList<ArrayList<Integer>> list) {
        if (root == null) {
            return;
        }

        while (list.size() < level + 1) {
            list.add(new ArrayList<>());
        }
        ArrayList<Integer> l = list.get(level);

        l.add(root.val);
        level(level + 1, root.left, list);
        level(level + 1, root.right, list);
    }
}
