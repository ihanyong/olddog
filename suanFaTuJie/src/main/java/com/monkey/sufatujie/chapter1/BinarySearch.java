package com.monkey.sufatujie.chapter1;

/**
 * BinarySearch
 *
 * @author yong.han
 * 2018/12/11
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] sortedArray = new int[]{0,1,6,8,14,65,89,100};

        int target = 13;

        System.out.println(binarySearch(sortedArray, 13, 0, sortedArray.length - 1));



    }

    public static int binarySearch(int[] sortedArray, int target, int minIndex, int maxIndex) {
        if (minIndex > maxIndex) {
            return -1;
        }

        int index = (minIndex + maxIndex) / 2;
        if (sortedArray[index] == target) {
            return index;
        } else if (sortedArray[index] > target) {
            return binarySearch(sortedArray, target, minIndex, index-1);
        } else {
            return binarySearch(sortedArray, target, index+1, maxIndex);
        }
    }


}
