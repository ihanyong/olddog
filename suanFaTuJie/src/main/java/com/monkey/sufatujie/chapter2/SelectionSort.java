package com.monkey.sufatujie.chapter2;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import com.monkey.sufatujie.IntSorter;

import java.util.Random;
import java.util.StringJoiner;

/**
 * SelectionSort
 *
 * @author yong.han
 * 2018/12/13
 */
public class SelectionSort implements IntSorter {

    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }

}
