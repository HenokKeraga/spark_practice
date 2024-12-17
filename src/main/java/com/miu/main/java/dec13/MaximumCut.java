package com.miu.main.java.dec13;

public class MaximumCut {

    public static void main(String[] args) {
         System.out.println(computeMaxCut(5, 1, 2, 3));
         System.out.println(dp2(5, 1, 2, 3));
        System.out.println(computeMaxCut2(5, 1, 2, 3));
        System.out.println(dpSolution(5, 1, 2, 3));
    }

    public static int computeMaxCut2(int n, int a, int b, int c) {
        int[] cuts = {a, b, c};


        return helpers(n, cuts, cuts.length - 1);

    }

    private static int helpers(int n, int[] cuts, int index) {

        if (n == 0) {
            return 0;
        }
        if (n < 0) {
            return -1;
        }
        if (index < 0) {
            return -1;
        }


        int exclude = helpers(n, cuts, index - 1);

        int include = helpers(n - cuts[index], cuts, index);
        include = include != -1 ? include + 1 : include;

        return Math.max(exclude, include);

    }

    private static int dpSolution(int n, int a, int b, int c) {
        int[] cuts = {a, b, c};
        int m = cuts.length;
        // the max number of cuts that can be made on rod of length j using the first i cuts
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = -1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int exclude = dp[i - 1][j];
                int include = -1;
                if (j - i >= 0) {
                    include = dp[i][j - i];
                }
                include = include != -1 ? include + 1 : include;
                dp[i][j] = Math.max(exclude, include);
            }

        }

        return dp[m][n];
    }

    public static int computeMaxCut(int n, int a, int b, int c) {

        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }


        int maxA = computeMaxCut(n - a, a, b, c);
        int maxB = computeMaxCut(n - b, a, b, c);
        int macC = computeMaxCut(n - c, a, b, c);

        int result = Math.max(Math.max(maxA, maxB), macC);

        if (result == -1) {
            return result;
        } else {
            return result + 1;
        }
    }

    public static int dp2(int n, int a, int b, int c) {
        int[] dp = new int[n + 1];// the maximum number of cuts can be made for a rod of length i


        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            int maxA = i - a >= 0 ? dp[i - a] : -1;
            int maxB = i - b >= 0 ? dp[i - b] : -1;
            int maxC = i - c >= 0 ? dp[i - c] : -1;
            int result = Math.max(Math.max(maxA, maxB), maxC);
            if(result==-1){
                dp[i] = -1;
            }else {
                dp[i]=result+1;
            }


        }


        return dp[n];
    }
}
