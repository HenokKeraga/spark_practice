package com.miu.main.java.dec7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmericanFootball {

    public static void main(String[] args) {

        //System.out.println(numCombinationsForFinalScore2(9, Arrays.asList(7, 6, 2, 3)));
        System.out.println(dp2(9, Arrays.asList(7, 6, 2, 3)));
    }

    public static int numCombinationsForFinalScore(
            int finalScore, List<Integer> individualPlayScores) {


        return helper(finalScore, individualPlayScores, 0);
    }

    public static int helper(int finalScore, List<Integer> individualPlayScores, int index) {
        if (finalScore == 0) {
            return 1;
        }
        if (finalScore < 0) {
            return 0;
        }
        int result = 0;
        for (int i = index; i < individualPlayScores.size(); i++) {
            result += helper(finalScore - individualPlayScores.get(i), individualPlayScores, i);
        }
        return result;
    }

    public static int numCombinationsForFinalScore1(
            int finalScore, List<Integer> individualPlayScores) {

        return helper1(finalScore, individualPlayScores, 0);
    }

    public static int helper1(int finalScore, List<Integer> individualPlayScores, int index) {
        if (finalScore == 0) {
            return 1; // Found a valid combination
        }
        if (finalScore < 0) {
            return 0; // No valid combination
        }
        if (index >= individualPlayScores.size()) {
            return 0;
        }

        // Include the current play score (reduce finalScore, stay on the same index)
        int includeCurrent = helper1(finalScore - individualPlayScores.get(index), individualPlayScores, index);

        // Exclude the current play score (move to the next index)
        int excludeCurrent = helper1(finalScore, individualPlayScores, index + 1);

        return includeCurrent + excludeCurrent;
    }

    public static int numCombinationsForFinalScore2(
            int finalScore, List<Integer> individualPlayScores) {

        return helper3(individualPlayScores, finalScore, individualPlayScores.size());
    }

    private static int helper3(List<Integer> individualPlayScores, int finalScore, int index) {
        if (finalScore < 0) {
            return 0;
        }
        if (finalScore == 0) {
            return 1;
        }
        if (index <= 0) {
            return 0;
        }

        int left = helper3(individualPlayScores, finalScore, index - 1); // skip
        int right = helper3(individualPlayScores, finalScore - individualPlayScores.get(index - 1), index);//use the current sc
        return left + right;
    }

    public static int dp(int finalScore, List<Integer> individualPlayScores) {

        int[] dp = new int[finalScore + 1];

        dp[0] = 1;
        for (Integer is : individualPlayScores) {
            for (int i = is; i <= finalScore; i++) {
                dp[i] = dp[i] + dp[i - is];

            }

        }


        return dp[finalScore];

    }

    public static int dp2(int finalScore, List<Integer> individualPlayScores) {
        // Create a 2D DP array
        int[][] dp = new int[individualPlayScores.size() + 1][finalScore + 1];

        // Base case: There is one way to achieve a score of 0 (by doing nothing)
        for (int i = 0; i <= individualPlayScores.size(); ++i) {
            dp[i][0] = 1;
        }

        // Fill the DP table
        for (int i = 1; i <= individualPlayScores.size(); ++i) {
            for (int j = 1; j <= finalScore; ++j) {
                // Without using the current play score
                int withoutThisPlay = dp[i - 1][j];


                // With using the current play score
                int withThisPlay = 0;
                if (j >= individualPlayScores.get(i - 1)) {
                    withThisPlay = dp[i][j - individualPlayScores.get(i - 1)];
                }


                // Total combinations
                dp[i][j] = withoutThisPlay + withThisPlay;
            }
        }

        // Return the total combinations for finalScore using all play scores
        return dp[individualPlayScores.size()][finalScore];
    }

}
