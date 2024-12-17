package com.miu.main.java.dec13;

public class MinimumCoin {
    public static void main(String[] args) {
        int[] coins = {25, 10, 5};
        int value = 30;

        // System.out.println(minCoin(coins, value));
        System.out.println(minCoin2(coins, value));

    }

    private static int minCoin(int[] coins, int value) {
        return helpercoint(coins, value, coins.length - 1);
    }

    private static int minCoin2(int[] coins, int value) {
        if (value == 0) {
            return 0;
        }
        if (value < 0) {
            return Integer.MAX_VALUE;
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int minV = minCoin2(coins, value - coins[i]);
            if (minV < Integer.MAX_VALUE) {
                minV++;
            }

            result = Math.min(result, minV);

        }
        return result;
    }

    private static int helpercoint(int[] coins, int value, int index) {

        if (value == 0) {
            return 0;
        }
        if (value < 0) {
            return Integer.MAX_VALUE;
        }
        if (index < 0) {
            return Integer.MAX_VALUE;
        }


        int exclude = helpercoint(coins, value, index - 1);
        int include = helpercoint(coins, value - coins[index], index);
        if (include < Integer.MAX_VALUE) {
            include++;
        }
        return Math.min(exclude, include);
    }

}
