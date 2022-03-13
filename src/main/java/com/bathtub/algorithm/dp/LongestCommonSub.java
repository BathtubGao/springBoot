package com.bathtub.algorithm.dp;


/**
 * 最长公共子序列
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 *
 * @author 17031612
 * @date 2022/1/12
 */
public class LongestCommonSub {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcba", "abcbcba"));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int longest = 0;
        if (chars1.length >= chars2.length) {
            longest = getLongest(chars1, chars2);
        } else {
            longest = getLongest(chars2, chars1);
        }
        return longest;
    }

    private static int getLongest(char[] chars1, char[] chars2) {
        int[] dp = new int[chars2.length+1];
        for (int j = 0; j < chars1.length; j++ ) {
            int cur = 0;
            for (int i = 1; i <= chars2.length; i++) {
                int leftTop = cur;
                cur = dp[i];
                if (chars1[j] == chars2[i-1]) {
                    dp[i] = leftTop + 1;
                } else {
                    dp[i] =  Math.max(dp[i], dp[i-1]);
                }
            }
        }
        return dp[chars2.length];
    }
}
