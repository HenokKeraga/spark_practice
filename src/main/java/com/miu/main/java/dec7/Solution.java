package com.miu.main.java.dec7;

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return ""; // Handle edge case of empty string

        // Create a 2D DP table to track palindromes
        boolean[][] dp = new boolean[n][n];

        // Single-character substrings are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        String best = s.substring(0, 1); // Initialize with the first character

        // Fill the DP table
        for (int j = 1; j < n; j++) { // Outer loop for end index
            for (int i = j - 1; i >= 0; i--) { // Inner loop for start index
                // Check if s[i..j] is a palindrome
                if (s.charAt(i) == s.charAt(j) && (i + 1 == j || dp[i + 1][j - 1])) {
                    dp[i][j] = true;

                    // Update the longest palindrome if necessary
                    if (j - i + 1 > best.length()) {
                        best = s.substring(i, j + 1);
                    }
                }
            }
        }

        return best;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("abccba")); // Output: "bab" or "aba"
    }
}

