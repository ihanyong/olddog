package com.monkey.sufatujie;

import com.google.common.primitives.Ints;
import com.monkey.sufatujie.chapter2.SelectionSort;
import com.monkey.sufatujie.chapter4.QuickSort;

import java.util.Random;

/**
 * SelectionSort
 *
 * @author yong.han
 * 2018/12/13
 */
public class SortMain {
    public static void main(String[] args) {
//        IntSorter intSorter = new SelectionSort();
        IntSorter intSorter = getSorter("quick");

        int[] array = randomArr(10);
        System.out.println("before sorted: " + join(array));
        array = intSorter.sort(array);
        System.out.println("after  sorted: " + join(array));
    }

    public static IntSorter getSorter(String type) {
        if ("selection".equals(type)) {
            return new SelectionSort();
        } else if ("quick".equals(type)) {
            return new QuickSort();
        }
        throw new RuntimeException();
    }



    public static int[] randomArr(int len) {
//        Math.random().
        Random random =  new Random();

        int[] arr = new int[len];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        return arr;
    }

    public static String join(int[] arr) {
//         return Joiner.on(",").join(Ints.asList(arr));
        return Ints.join(",", arr);
//        StringJoiner sj = new StringJoiner(",", "[", "]");
//        for (int i : arr) {
//            sj.add(String.valueOf(i));
//        }
//        return sj.toString();
    }

}
