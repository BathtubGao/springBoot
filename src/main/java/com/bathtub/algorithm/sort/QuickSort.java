package com.bathtub.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author 17031612
 * @date 2021/12/27
 */
public class QuickSort {
    private static int[] arr = {14, 7, 3, 20, 21, 5, 1, 13, 9, 2, 6};

    public static void sort(int begin, int end) {
        if (end - begin <= 1) {
            return;
        }
        int baseValue = arr[begin];
        int sbegin = begin,send = end-1;
        while(send > sbegin) {
            while (send > sbegin) {
                if (arr[send] > baseValue) {
                    send--;
                } else {
                    arr[sbegin++] = arr[send];
                    break;
                }
            }
            while (send > sbegin) {
                if (arr[sbegin] < baseValue) {
                    sbegin++;
                } else {
                    arr[send--] = arr[sbegin];
                    break;
                }
            }
        }
        arr[sbegin] = baseValue;

        sort(begin, sbegin);
        sort(sbegin+1, end);
    }

    public static void main(String[] args) {
        sort(0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
