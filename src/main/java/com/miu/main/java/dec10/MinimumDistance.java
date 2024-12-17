package com.miu.main.java.dec10;

public class MinimumDistance {
    public static void main(String[] args) {
        String s1 = "saturday";
        String s2 = "sunday";
        System.out.println(dp(s1, s1.length(), s2, s2.length()));
    }

    public static int minDistance(String s1, int m, String s2, int n) {

        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }


        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return minDistance(s1, m - 1, s2, n - 1);
        } else {

            return Math.min(
                    Math.min(
                            1 + minDistance(s1, m - 1, s2, n), //deletion
                            1 + minDistance(s1, m, s2, n - 1) //insertion
                    ),
                    1 + minDistance(s1, m - 1, s2, n - 1) // replace
            );
        }

    }

    public static int dp(String s1, int m, String s2, int n) {

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;//m column
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // n row
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }


            }

        }

        return dp[m][n];

    }
}
