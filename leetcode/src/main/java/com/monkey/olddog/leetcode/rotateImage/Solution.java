package com.monkey.olddog.leetcode.rotateImage;

import java.util.Arrays;

/**
 * Solution
 *
 * @author yong.han
 * 2018/9/13
 */
public class Solution {
//
//    给定一个 n × n 的二维矩阵表示一个图像。
//    将图像顺时针旋转 90 度。
//    说明：
//    你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

    // 先转置， 再逆序
    public static void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i+1; j <matrix.length; j++) {
                matrix[i][j] += matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] -=  matrix[j][i];
            }
        }

        for (int i = 0; i <matrix.length; i++) {
            for (int j = 0; j <= (matrix.length - 1) / 2; j++) {
                matrix[i][j] += matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = matrix[i][j] - matrix[i][matrix.length - 1 - j];
                matrix[i][j] -= matrix[i][matrix.length - 1 - j];
            }
        }
    }

    public static void main(String[] args) {
        int [][] input = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(input);

        System.out.println(input);

    }
}
