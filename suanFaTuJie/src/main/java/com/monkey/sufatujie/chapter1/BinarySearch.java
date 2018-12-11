package com.monkey.sufatujie.chapter1;

/**
 * BinarySearch
 *
 * @author yong.han
 * 2018/12/11
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] sortedArray = new int[]{};

        int target = 13;





    }

    public static int binarySearch(int[] sortedArray, int target, int minIndex, int maxIndex) {
        int index = (minIndex + maxIndex) / 2;
        if (sortedArray[index] == target) {
            return index;
        } else if (sortedArray[index] > target) {
            return binarySearch(sortedArray, target, minIndex, index-1);
        } else {
            return binarySearch(sortedArray, target, index+1, index);
        }
    }


}
