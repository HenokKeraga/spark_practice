package com.miu.main.java.dec5;

public class MinimizeHeights {

    // Recursive function to implement the recurrence relation
    public static int minimizeHeightRecursive(int[] heights, int n, int k, int currentMin, int currentMax) {
        // Base case: No towers left
        if (n == 0) {
            return currentMax - currentMin; // Difference between tallest and shortest
        }

        // Current tower height (1-based indexing, so we use n-1 for 0-based array)
        int currentHeight = heights[n - 1];

        // Case 1: Add K to the current height
        int option1 = minimizeHeightRecursive(
                heights,
                n - 1,
                k,
                Math.min(currentMin, currentHeight + k),
                Math.max(currentMax, currentHeight + k)
        );

        // Case 2: Subtract K from the current height
        int option2 = minimizeHeightRecursive(
                heights,
                n - 1,
                k,
                Math.min(currentMin, currentHeight - k),
                Math.max(currentMax, currentHeight - k)
        );

        // Return the minimum difference between the two options
        return Math.min(option1, option2);
    }

    public static void main(String[] args) {
        int[] heights = {1, 15, 10}; // Example input
        int k = 6; // Adjustment value
        int n = heights.length;

        // Use the last tower's height to initialize the recursive call
        int initialHeight = heights[n - 1]; // Last tower's height
        int result = minimizeHeightRecursive(
                heights,
                n, // Start with all towers
                k,
                initialHeight, // Initialize min with the last tower's height
                initialHeight  // Initialize max with the last tower's height
        );

        System.out.println("The minimum difference is: " + result);
    }
}


