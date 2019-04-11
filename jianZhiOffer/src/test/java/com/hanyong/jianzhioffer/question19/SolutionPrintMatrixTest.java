package com.hanyong.jianzhioffer.question19;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * SolutionPrintMatrixTest
 *
 * @author yong.han
 * 2019/4/11
 */
public class SolutionPrintMatrixTest {
    @Test
    public void test() {
        SolutionPrintMatrix s = new SolutionPrintMatrix();

        assertArrayEquals(new Integer[]{}, s.printMatrix(new int[][]{{}, {}}).toArray());
        assertArrayEquals(new Integer[]{1}, s.printMatrix(new int[][]{{1}}).toArray());
        assertArrayEquals(new Integer[]{1, 2, 3}, s.printMatrix(new int[][]{{1}, {2}, {3}}).toArray());
        assertArrayEquals(new Integer[]{1, 2, 3}, s.printMatrix(new int[][]{{1, 2, 3}}).toArray());
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10}, s.printMatrix(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}).toArray());
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 10, 15, 20, 19, 18, 17, 16, 11, 6, 7, 8, 9, 14, 13, 12}, s.printMatrix(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10,}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}}).toArray());
    }
}
