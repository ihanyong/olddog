package com.monkey.olddog.leetcode.isValidSudoku;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Solution
 *
 * @author yong.han
 * 2018/9/13
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println((int)'0');
        System.out.println((int)'1');
        System.out.println((int)'2');
        System.out.println((int)'3');

    }

    /**
     *
     一个有效的数独（部分已被填充）不一定是可解的。
     只需要根据以上规则，验证已经填入的数字是否有效即可。
     给定数独序列只包含数字 1-9 和字符 '.' 。
     给定数独永远是 9x9 形式的。

     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        // 检测 27个组的数字是否有重复

        int [][][] p = new int [][][] {
                {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}}
        ,{{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}}
        ,{{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}}};

        for (int i = 0; i < 9; i++) {
            int[] countersX = new int[9];
            int[] countersY = new int[9];


            for (int j = 0; j < 9; j++) {
                int x = (board[i][j]-49);
                if (x >= 0) {
                    // x 轴
                    if ( countersX[x]> 0) {
                        return false;
                    }
                    countersX[x]++;

                    // p
                    if (x >= 0) {
                        if (p[(i + 1) / 3][(j + 1) / 3][x] > 0) {
                            return false;
                        }
                        p[(i + 1) / 3][(j + 1) / 3][x]++;
                    }
                }

                // y 轴
                int y = (board[j][i] - 49);
                if (y >= 0) {
                    if (countersY[y] > 0) {
                        return false;
                    }
                    countersY[y]++;
                }

                // p



            }




        }




        return true;
    }


    public boolean isValid(char[] chars) {
        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            if ('.' != c) {
                if (!set.contains(c)) {
                    set.add(c);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
