package com.miu.main.java.dec13;

public class Knapsack01 {
    public static void main(String[] args) {
        int w = 10;
        int[] wt = {5, 4, 6, 3};
        int[] v = {10, 40, 30, 50};
        System.out.println(knapsack(w, wt, v, wt.length - 1));
        System.out.println(knapsack2(w, wt, v, wt.length - 1));
        System.out.println(dpf(w, wt, v, wt.length - 1));
    }

    private static int dpf(int w, int[] wt, int[] v, int index) {
        int m = wt.length;
        int[][] dp = new int[m+1][w + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= w; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= w; j++) {
                int exclude = dp[i - 1][j];
                int include = 0;
                if (j - wt[i-1] >= 0) {
                    include = v[i-1] + dp[i - 1][j - wt[i-1]];
                }
                dp[i][j] = Math.max(exclude, include);
            }
        }


        return dp[m][w];
    }

    public static int knapsack(int w, int[] wt, int[] v, int index) {
        if (w == 0) {
            return 0;
        }
        if (index < 0) {
            return 0;
        }


        int exclude = knapsack(w, wt, v, index - 1);
        int included = 0;
        if (w - wt[index] >= 0) {
            included = v[index] + knapsack(w - wt[index], wt, v, index - 1);
        }


        return Math.max(exclude, included);
    }

    public static int knapsack2(int w, int[] wt, int[] v, int index) {
        if (w == 0) {
            return 0;
        }
        if (index < 0) {
            return 0;
        }
        if (w - wt[index] >= 0) {
            return Math.max(v[index] + knapsack(w - wt[index], wt, v, index - 1), knapsack(w, wt, v, index - 1));
        } else {
            return knapsack(w, wt, v, index - 1);
        }

    }
}
