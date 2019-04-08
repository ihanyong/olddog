package com.hanyong.jianzhioffer.question1;

/**
 * Solution
 *
 *
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * @author yong.han
 * 2019/4/8
 */
public class Solution {
    public boolean Find(int target, int [][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (target == array[i][0] || target == array[i][array[0].length - 1]) {
                return true;
            } else if (target > array[i][0] && target < array[i][array[0].length - 1]) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] == target) {
                        return true;
                    }
                }
            }
        }
        return false;

    }
}
