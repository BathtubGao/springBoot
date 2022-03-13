package com.bathtub.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 * @author 17031612
 * @date 2021/12/24
 */
public class HeapSort {

    public static void heapSort(int[] arr) {
        // 建堆
        sortArr(arr, arr.length);
        for (int end = arr.length-1;end > 0; end--) {
            int temp = arr[0];
            arr[0] = arr[end];
            arr[end] = temp;
            sortArr(arr, end);
        }
    }

    private static void sortArr(int[] arr, int height) {
        if (height  == 0) {
            return;
        }
        int i = (height >> 1) - 1;
        while (i >= 0) {
            int tempIndex = (i << 1) + 1; // 左节点
            if ((tempIndex + 1) < height && arr[tempIndex + 1] > arr[tempIndex]) {
                tempIndex = tempIndex + 1; // 存在右节点且大于左节点
            }
            if (arr[i] < arr[tempIndex]) {
                int temp = arr[i];
                arr[i] = arr[tempIndex];
                arr[tempIndex] = temp;
            }
            i--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {16, 7, 3, 20, 21, 8, 1, 33, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
