package com.miu.main.java.nov18;

import java.util.Arrays;

public class MaximumProfit {
    public static void main(String[] args) {

        System.out.println(maximumProfit(new int[]{100, 180, 260, 310, 40, 535, 695}));
    }

    public static int maximumProfit(int prices[]) {
        // code here

        int[] diff = new int[prices.length];

        for (int i = 1; i < prices.length; i++) {

            diff[i] = prices[i] - prices[i - 1];

        }


        int i = 0;
        int j = 0;

        int maxS = 0;

        while (j < diff.length) {

            while (j < diff.length && diff[j] >= diff[i]) {
                j++;
            }
            while (i < j) {
                maxS += diff[i];
                i++;
            }


        }

        while (i < j) {
            maxS += diff[i];
            i++;
        }


        return maxS;

    }
}
