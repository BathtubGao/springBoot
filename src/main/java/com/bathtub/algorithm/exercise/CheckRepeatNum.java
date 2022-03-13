package com.bathtub.algorithm.exercise;

/**
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * TODO 如果数字不在 0～n-1 的范围内
 */
public class CheckRepeatNum {
    public static int checkRepeatNum(int[] arr) {
        int index = -1;
        while (index < arr.length) {
            if (arr[index] < arr.length) {
                if (arr[index] == index) {
                    index++;
                    continue;
                }
                if (arr[arr[index]] == arr[index]) {
                    return arr[index];
                }
                int temp = arr[index];
                arr[index] = arr[temp];
                arr[temp] = temp;
            } else {
                index++;
            }
        }
        return index;
    }


    public static void main(String[] args) {
        int[] arr = {4, 3, 1, 0, 5, 4, 6};
        System.out.println(checkRepeatNum(arr));
    }
}
