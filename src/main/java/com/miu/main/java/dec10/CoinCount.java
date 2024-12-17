package com.miu.main.java.dec10;

public class CoinCount {
    public static void main(String[] args) {
        int[] coins = {1,2,3};

        System.out.println(coinCount(coins,3,4));
    }


    public static int coinCount(int[] coin, int n, int s) {

        int[][] dp = new int[n + 1][s + 1];
        //base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= s; j++) {
                int left = 0;
                if ( coin[i-1] <= j ) {
                    left = dp[i][j - coin[i-1]];
                }
                int right  = dp[i - 1][j];

                dp[i][j] = left + right;

            }
        }


        return dp[n][s];
    }
}
