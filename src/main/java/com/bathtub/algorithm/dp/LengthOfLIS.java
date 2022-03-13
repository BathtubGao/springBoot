package com.bathtub.algorithm.dp;

/**
 * 最长上升子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * @author 17031612
 * @date 2022/1/10
 */
public class LengthOfLIS {
    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(new LengthOfLIS().lengthOfLIS(nums));
    }

    int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j])
                    continue;
                dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
