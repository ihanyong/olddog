package com.monkey.olddog.leetcode.binary.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * @author hanyong
 * @Date 2018/10/5
 */
public class LevelOrder {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        flat(root, 0, list);
        return list;
    }

    public static void flat(TreeNode node, int level,  List<List<Integer>> list) {
        if (null == node) {
            return;
        }

        if (list.size() <= level) {
            list.add(new ArrayList<>());
        }
        list.get(level).add(node.val);

        flat(node.left, level + 1, list);
        flat(node.right, level + 1, list);
    }
}
