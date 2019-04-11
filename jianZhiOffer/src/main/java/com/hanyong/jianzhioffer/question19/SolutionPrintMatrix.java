package com.hanyong.jianzhioffer.question19;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * SolutionPrintMatrix
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionPrintMatrix {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (null == matrix || matrix[0] == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> list = new ArrayList<>(matrix.length * matrix[0].length);

        Pointer p = new Pointer(matrix.length, matrix[0].length);
        while (p.hasNext()) {
            int[] point = p.next();
            list.add(matrix[point[0]][point[1]]);
        }
        return list;
    }

    public static class Pointer implements Iterator<int[]> {
        int xL;
        int yL;
        int[] boundaryX;
        int[] boundaryY;

        // changeDirect%4 =    0: +x, 1:+y, 2:-x, 3:-y
        int changeDirect;
        int count;
        int[] current = new int[]{0, -1};
        public Pointer(int yL, int xL) {
            this.xL = xL;
            this.yL = yL;
            this.boundaryX = new int[]{0, xL - 1};
            this.boundaryY = new int[]{1, yL - 1};
        }
        private void changeDirect() {
            int currentMove = changeDirect % 4;
            switch (currentMove) { // 0: +x, 1:+y, 2:-x, 3:-y
                case 0:
                    if (current[1] == boundaryX[1]) {
                        changeDirect++;
                        boundaryX[1]--;
                    }
                    break;
                case 1:
                    if (current[0] == boundaryY[1]) {
                        changeDirect++;
                        boundaryY[1]--;
                    }
                    break;
                case 2:
                    if (current[1] == boundaryX[0]) {
                        changeDirect++;
                        boundaryX[0]++;
                    }
                    break;
                case 3:
                    if (current[0] == boundaryY[0]) {
                        changeDirect++;
                        boundaryY[0]++;
                    }
                    break;
                default:
                    break;

            }
        }

        @Override
        public int[] next() {
            // terminate?
            if (count >= xL * yL) {
                return null;
            }
            count++;

            // change direct?
            changeDirect();

            // move
            int move = changeDirect % 4;
            switch (move) { // 0: +x, 1:+y, 2:-x, 3:-y
                case 0:
                    current[1]++;
                    break;
                case 1:
                    current[0]++;
                    break;
                case 2:
                    current[1]--;
                    break;
                case 3:
                    current[0]--;
                    break;
                default:
                    break;
            }
            return current;
        }

        @Override
        public boolean hasNext() {
            return count < xL * yL;
        }

    }
}
