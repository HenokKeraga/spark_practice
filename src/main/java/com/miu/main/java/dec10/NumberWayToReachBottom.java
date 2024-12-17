package com.miu.main.java.dec10;

public class NumberWayToReachBottom {
    public static void main(String[] args) {
        System.out.println(numberOfWays(5, 5));
    }

    //where n is the
//number of rows and m is the number of columns.
    public static int numberOfWays(int n, int m) {


        return dp(n - 1, m - 1);
    }


    public static int helper(int r, int c) {

        if (r == 0 || c == 0) {
            return 1;
        }


        return helper(r - 1, c) + helper(r, c - 1);
    }

    public static int dp(int r, int c) {
        int[][] dp = new int[r + 1][c + 1];

        for (int j = 0; j <= c; j++) {
            dp[0][j] = 1;
        }
        for (int i = 0; i <= r; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {

                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }

        }


        return dp[r][c];
    }
}
