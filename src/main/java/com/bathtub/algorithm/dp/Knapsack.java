package com.bathtub.algorithm.dp;


/**
 * 有 n 件物品和一个最大承重为 W 的背包，每件物品的重量是 𝑤i、价值是 𝑣i
 * 在保证总重量不超过 W 的前提下，选择某些物品装入背包，背包的最大总价值是多少？
 * 注意：每个物品只有 1 件，也就是每个物品只能选择 0 件或者 1 件
 * @author 17031612
 * @date 2022/1/13
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] wi = new int[]{2,2,6,5,4};
        int[] vi = new int[]{6,3,5,4,6};
        System.out.println(getMaxValue(10, 5, wi, vi));
    }
    private static int getMaxValue(int w, int n, int[] wi, int[] vi) {
        int[] temp = new int[w+1];
        for(int i = 0; i < n; i++) {
            for (int end = w; end > 0; end--) {
                if (wi[i] > end)
                    continue;
                temp[end] = Math.max(temp[end-wi[i]] + vi[i], temp[end]);
            }
        }
        return temp[w];
    }
}
