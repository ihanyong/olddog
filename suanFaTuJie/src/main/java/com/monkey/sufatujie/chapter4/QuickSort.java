package com.monkey.sufatujie.chapter4;

import com.monkey.sufatujie.IntSorter;

/**
 * QuickSort
 *
 * @author yong.han
 * 2018/12/13
 */
public class QuickSort implements IntSorter {


    @Override
    public int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    // 基线条件: left >= right
    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = partition(array, left, right);

        quickSort(array, left, index);
        quickSort(array, index + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int index = left + 1;

        for (int i = index; i <= right; i++) {
            if (array[i] < array[left]) {
                swap(array, index, i);
                index++;
            }
        }
        int pivot = index - 1;

        swap(array, left, pivot);
        return pivot;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
