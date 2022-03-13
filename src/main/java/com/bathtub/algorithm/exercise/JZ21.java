package com.bathtub.algorithm.exercise;

import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/ef1f53ef31ca408cada5093c8780f44b
 * @author 17031612
 * @date 2022/1/26
 */
public class JZ21 {

    public static void main(String[] args) {
        int[] arr = new int[]{2,16,3,5,13,1,16,1,12,18,11,8,11,11,5,1};
        System.out.println(Arrays.toString(reOrderArray(arr)));
    }

    public static int[] reOrderArray (int[] array) {
        int begin = 0;
        int end = array.length - 1;
        while (begin <= end) {
            if ((array[begin] & 1) == 1) {
                begin++;
                continue;
            }
            if ((array[end] & 1) == 0) {
                end--;
                continue;
            }
            cmp(array, begin++, end--);
        }

        // 排序
        sort(array, 0, begin);
        sort(array, begin, array.length);
        return array;
    }

   private static void sort(int[] array, int begin, int end) {
        for (int i = begin + 1; i < end; i++) {
            int cur = i;
            while (cur > begin && array[cur] < array[cur-1]) {
                cmp(array, cur, --cur);
            }
        }
   }

    private static void cmp(int[] array, int m, int n) {
        int temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }
}
