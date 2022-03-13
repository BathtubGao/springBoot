package com.bathtub.algorithm.exercise;

import java.util.Arrays;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 */
public class MaxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = new int[]{};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 0)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return nums;
        if (k == 1) return nums;
        int end = nums.length - k + 1;
        int[] maxInts = new int[end];
        if (k == 0) return maxInts;
        int maxIndex = getMaxIndex(nums, 0, k-1);
        maxInts[0] = nums[maxIndex];
        for (int i = 1; i < end; i++) {
            int iend = i + k-1;
            if (maxIndex < i) {
                maxIndex = getMaxIndex(nums, i, iend);
            } else {
                if (nums[iend] >= nums[maxIndex]) {
                    maxIndex = iend;
                }
            }
            maxInts[i] = nums[maxIndex];
        }
        return maxInts;
    }

    private static int getMaxIndex(int[] nums, int begin, int end) {
        int maxIndex = begin++;
        for (; begin <= end; begin++) {
            if (nums[begin] >= nums[maxIndex]) {
                maxIndex = begin;
            }
        }
        return maxIndex;
    }
}

