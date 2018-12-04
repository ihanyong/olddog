package com.monkey.olddog.leetcode;

/**
 * @author hanyong
 * @Date 2018/10/5
 */
public class SortAndSearch {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        for (int i = 0; i < m; i++) {
            nums1[nums1.length - 1 - i] = nums1[m - 1 - i];
        }
        int index1 = nums1.length - m;
        int index2 = 0;

        for (int i = 0; i < nums1.length; i++) {
            if (i == m) {
                for (int j = i; j < nums1.length; j++, index2++) {
                    nums1[i] = nums2[index2++];
                }
                return;
            }
            if (i == n) {
                for (int j = i; j < nums1.length; j++, index1++) {
                    nums1[i] = nums1[index1++];
                }
                return;
            }

            if (nums1[index1] < nums2[index2]) {
                nums1[i] = nums1[index1++];
            } else {
                nums1[i] = nums2[index2++];
            }
        }
    }


//    public static void  printArray(int... arr) {
//        System.out.println(String.join(",", arr));
//    }
}
