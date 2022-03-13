package com.bathtub.algorithm.dp;

/**
 * 找零钱
 * @author 17031612
 * @date 2022/1/10
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = new int[]{2};
        System.out.println(new CoinChange().coinChange_db(coins, 3));
    }

    /**
     * 递归遍历，时耗高
     */
    public int coinChange_recursion(int[] coins, int amount) {
        if (coins.length == 0) return -1;
        if (amount == 0) return 0;
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount < coin) continue;
            if (coin == amount) return 1;
            int beforeCoin = coinChange_recursion(coins, amount-coin);
            if (beforeCoin == -1) continue;
            min = Math.min(min, beforeCoin) + 1;
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int coinChange_db(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i < coin)
                    continue;
                if (dp[i-coin] < 0 || dp[i-coin] >= min)
                    continue;
                min = dp[i-coin];
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[amount];
    }

}
