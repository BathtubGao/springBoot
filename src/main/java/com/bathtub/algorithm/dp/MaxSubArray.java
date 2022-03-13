package com.bathtub.algorithm.dp;

/**
 * 最大连续子序列
 * 给定一个长度为 n 的整数序列，求它的最大连续子序列和
 * 比如 –2、1、–3、4、–1、2、1、–5、4 的最大连续子序列和是 4 + (–1) + 2 + 1 = 6
 * @author 17031612
 * @date 2022/1/10
 */
public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(new MaxSubArray().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4, 10}));
    }

    int maxSubArray(int[] arr) {
        if (arr.length == 0) return 0;
        int dp = arr[0];
        int max = dp;
        for (int i = 1; i < arr.length; i++) {
            dp = Math.max(dp + arr[i], arr[i]);
            max = Math.max(dp, max);
        }
        return max;
    }
}
