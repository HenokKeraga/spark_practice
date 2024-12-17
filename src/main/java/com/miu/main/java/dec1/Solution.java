package com.miu.main.java.dec1;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {

//        System.out.println(getMinDiff(new int[]{1, 8, 10, 6, 4, 6, 9, 1}, 7));
        // System.out.println(getMinDiff(new int[]{1, 1, 4, 6, 6, 8, 9, 10}, 7));
        System.out.println(getMinDiff(new int[]{1, 2}, 2));
//        System.out.println(getMinDiff(new int[]{1, 5, 8, 10}, 2));

    }

    static int getMinDiff(int[] arr, int k) {
        // code here

        return helper(arr, arr.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, k);
    }


//    static int helper(int[] arr, int index, int max, int min, int k) {
//
//        if (index < 0) {
//            return max - min;
//        }
//
//        int currentHeight = arr[index];
//
//        int option1 = helper(arr, index - 1, Math.max(max, currentHeight + k), Math.min(min, arr[index] + k), k);
//        int option2 = helper(arr, index - 1, Math.max(max, currentHeight - k), Math.min(min, arr[index] - k), k);
//
//        return Math.min(option1, option2);
//
//
//    }

    static int helper(int[] arr, int index, int max, int min, int k) {

        if (index < 0) {
            return max - min;
        }

        int currentHeight = arr[index];

        int increaseHeight = currentHeight + k;
        //Dynamic Update  for the left branch
        int option1 = helper(arr, index - 1, Math.max(max, increaseHeight), Math.min(min, increaseHeight), k);

        int decreaseHeight = currentHeight - k;

        //Dynamic Update for the right branch
        int option2 = helper(arr, index - 1, Math.max(max, decreaseHeight), Math.min(min, decreaseHeight), k);

        return Math.min(option1, option2);


    }


//    static int getMinDiff(int[] arr, int k) {
//        int n = arr.length;
//        if (n == 1) return 0; // If there's only one tower, no difference can occur.
//
//        // Step 1: Sort the array
//        Arrays.sort(arr);
//
//        // Step 2: Initialize result with the initial difference
//        int result = arr[n - 1] - arr[0];
//
//        // Step 3: Iterate and calculate possible minimum and maximum heights
//        int smallest = arr[0] + k;
//        int largest = arr[n - 1] - k;
//
//        for (int i = 0; i < n - 1; i++) {
//            int minHeight = Math.min(smallest, arr[i + 1] - k);
//            int maxHeight = Math.max(largest, arr[i] + k);
//
//            // Ensure no negative heights
//            if (minHeight < 0) continue;
//
//            // Update result
//            result = Math.min(result, maxHeight - minHeight);
//        }
//
//        return result;
//    }

//    static  int getMinDiff(int[] arr, int k) {
//        int n = arr.length;
//        if (n == 1) return 0; // If only one tower, no difference can occur.
//
//        // Step 1: Sort the array
//        Arrays.sort(arr);
//
//
//
//        int minHeight = arr[0] + k; // Initialize with the smallest adjusted height
//        for (int i = 1; i < n; i++) { // Start from 1 as the first element is already used
//            if (arr[i] - k >= 0) { // Ensure no negative heights
//                minHeight = Math.min(minHeight, arr[i] - k);
//            }
//        }
//
//        // Step 4: Separate loop for maxHeight
//
//        int maxHeight = arr[n - 1] - k;; // Initialize with the largest adjusted height
//        for (int i = 0; i < n; i++) { // Include all elements for potential maximum
//            maxHeight = Math.max(maxHeight, arr[i] + k);
//        }
//
//
//
//        return maxHeight - minHeight;
//    }

//    static  int getMinDiff(int[] arr, int k) {
//        int n = arr.length;
//        if (n == 1) return 0; // If only one tower, no difference can occur.
//
//        // Step 1: Sort the array
//        Arrays.sort(arr);
//
//
//
//        int minHeight = Integer.MIN_VALUE; // Initialize with the smallest adjusted height
//        for (int i = 1; i < n; i++) { // Start from 1 as the first element is already used
//            if (arr[i] - k >= 0) { // Ensure no negative heights
//                minHeight = Math.max(minHeight, arr[i] - k);
//            }
//        }
//
//        // Step 4: Separate loop for maxHeight
//
//        int maxHeight = Integer.MAX_VALUE ; // Initialize with the largest adjusted height
//        for (int i = 0; i < n; i++) { // Include all elements for potential maximum
//            maxHeight = Math.min(maxHeight, arr[i] + k);
//        }
//
//
//
//        return  minHeight -maxHeight;
//    }

    //    public static int getMinDiff(int[] arr, int k) {
//        int n = arr.length;
//        // To store all combinations of +K and -K
//        int[] modified = new int[n];
//
//        // Call the recursive helper function
//        return helper(arr, modified, k, 0, n);
//    }
//
//    // Recursive function to generate all combinations
//    private static int helper(int[] arr, int[] modified, int k, int index, int n) {
//        if (index == n) {
//            // Compute the difference between max and min for this combination
//            int maxHeight = Integer.MIN_VALUE;
//            int minHeight = Integer.MAX_VALUE;
//
//            for (int height : modified) {
//                maxHeight = Math.max(maxHeight, height);
//                minHeight = Math.min(minHeight, height);
//            }
//
//            return maxHeight - minHeight;
//        }
//
//        // Modify the current tower by +K
//        modified[index] = arr[index] + k;
//        int add = helper(arr, modified, k, index + 1, n);
//
//        // Modify the current tower by -K
//        modified[index] = arr[index] - k;
//        int subtract = helper(arr, modified, k, index + 1, n);
//
//        // Return the minimum difference from both choices
//        return Math.min(add, subtract);
//    }
// Main function to calculate the minimum height difference
//    public static int getMinDiff(int[] arr, int k) {
//        if (arr == null || arr.length == 0) return 0;
//
//        // Initialize recursion with extreme values for maxHeight and minHeight
//        return findMinDifference(arr, k, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
//    }
//
//    private static int findMinDifference(int[] arr, int k, int index, int maxHeight, int minHeight) {
//        // Base case: all towers are processed
//        if (index == arr.length) {
//            return maxHeight - minHeight;
//        }
//
//        // Option 1: Increase the current tower's height by K
//        int increase = findMinDifference(
//                arr, k, index + 1,
//                Math.max(maxHeight, arr[index] + k),
//                Math.min(minHeight, arr[index] + k)
//        );
//
//        // Option 2: Decrease the current tower's height by K
//        int decrease = findMinDifference(
//                arr, k, index + 1,
//                Math.max(maxHeight, arr[index] - k),
//                Math.min(minHeight, arr[index] - k)
//        );
//
//        // Return the minimum difference
//        return Math.min(increase, decrease);
//    }

//    // Recursive helper function
//    private static int findMinDifference(int[] arr, int[] temp, int k, int index) {
//        // Base case: when all towers are processed
//        if (index == arr.length) {
//            // Find the difference between the max and min heights
//            int maxHeight = Arrays.stream(temp).max().getAsInt();
//            int minHeight = Arrays.stream(temp).min().getAsInt();
//            return maxHeight - minHeight;
//        }
//
//        // Option 1: Increase the height of the current tower by K
//        temp[index] = arr[index] + k;
//        int increase = findMinDifference(arr, temp, k, index + 1);
//
//        // Option 2: Decrease the height of the current tower by K
//        temp[index] = arr[index] - k;
//        int decrease = findMinDifference(arr, temp, k, index + 1);
//
//        // Restore the original value of temp[index] to ensure no side effects
//        temp[index] = arr[index];
//
//        // Return the minimum difference
//        return Math.min(increase, decrease);
//    }
//
//    // Main function to calculate the minimum height difference
//    public static int getMinDiff(int[] arr, int k) {
//        if (arr == null || arr.length == 0) return 0;
//
//        // Temporary array to store the modified heights
//        int[] temp = Arrays.copyOf(arr, arr.length);
//
//        // Start recursion from the first tower
//        return findMinDifference(arr, temp, k, 0);
//    }

//    int helper(int[] arr, int index, int max, int min, int k) {
//
//        if (index == arr.length) {
//            return max - min;
//        }
//
//        return Math.min(
//                helper(arr, index + 1, Math.max(max, arr[index] + k), Math.min(min, arr[index] + k), k)
//                ,
//
//                helper(arr, index + 1, Math.max(max, arr[index] - k), Math.min(min, arr[index] - k), k)
//        );
//
//
//    }


}

