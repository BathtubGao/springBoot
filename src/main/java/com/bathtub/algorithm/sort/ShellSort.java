package com.bathtub.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 希尔排序
 *
 * @author 17031612
 * @date 2021/12/27
 */
public class ShellSort {
    private static int[] arr = {14, 7, 3, 20, 21, 5, 1, 13, 9, 2, 6, 8, 1};

    private static void sort(int step) {
        for (int col = 0; col < step; col++) {
            for (int begin = col+step; begin < arr.length; begin+=step) {
                int cur = begin;
                int curValue = arr[cur];
                while (cur > col && curValue < arr[cur - step]) {
                    arr[cur] = arr[cur -= step];
                }
                arr[cur] = curValue;
            }
        }
//        for (int begin = 0; begin < arr.length; begin++) {
//            if (begin >= step) {
//                int cur = begin;
//                int curValue = arr[cur];
//                while (cur >= step && curValue < arr[cur - step]) {
//                    arr[cur] = arr[cur -= step];
//                }
//                arr[cur] = curValue;
//            }
//        }
    }

    private static void sort() {
        List<Integer> stepList = new ArrayList<>();
        stepList.add(4);
        stepList.add(2);
        stepList.add(1);
        stepList.stream().forEach(each ->
                sort(each)
        );
    }

    public static void main(String[] args) {
        sort();
        System.out.println(Arrays.toString(arr));
    }
}
