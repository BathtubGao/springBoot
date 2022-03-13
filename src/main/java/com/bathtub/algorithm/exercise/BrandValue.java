package com.bathtub.algorithm.exercise;

/**
 * 给定一个长度为n的整型数组，表示一个选手在n轮内可选择的牌面分数。选手基于规则选牌，请计算所有轮结束后其可以获得的最高总分数。
 * 选择规则如下：
 * 1、在每轮里选手可以选择获取该轮牌面，则其总分数加上该轮牌面分数，为其新的总分数。
 * 2、选手也可不选择本轮牌面直接跳到下一轮，此时将当前总分数还原为3轮前的总分数，若当前轮次小于等于3（即在第1、2、3轮选择跳过轮次），则总分数置为0。
 * 3、选手的初始总分数为0，且必须依次参加每一轮。
 */
public class BrandValue {
    public static void main(String[] args) {
        int[] ints = new int[]{2,-1,3,5,-10,3,4,-1,8};
        System.out.println(brandMax(ints));
    }

    private static int brandMax(int[] ints) {
        int[] dp = new int[ints.length];
        dp[0] = Math.max(ints[0], 0);
        for (int i = 1; i < ints.length; i++) {
            int value = ints[i] + dp[i-1];
            dp[i] = Math.max(getBeforeDp(dp, i), value);
        }
        return dp[ints.length-1];
    }

    private static int getBeforeDp(int[] dp, int index) {
        if (index <= 3)
            return 0;
        return dp[index-4];
    }
}
