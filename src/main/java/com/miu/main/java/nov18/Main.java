package com.miu.main.java.nov18;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println(numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        // HashMap to store prefix sums and their frequencies
        HashMap<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1); // Initialize with prefix sum 0

        int prefixSum = 0;
        int result = 0;

        for (int num : nums) {
            // Update the current prefix sum
            prefixSum += num;

            // Check if (prefixSum - goal) exists in the hashmap
            if (sumCount.containsKey(prefixSum - goal)) {
                result += sumCount.get(prefixSum - goal);
            }

            // Update the hashmap with the current prefix sum
            sumCount.put(prefixSum, sumCount.getOrDefault(prefixSum, 0) + 1);
        }

        return result;
    }

}
