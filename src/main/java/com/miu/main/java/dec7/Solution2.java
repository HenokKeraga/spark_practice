package com.miu.main.java.dec7;

class Solution2 {
    public static void main(String[] args) {

        System.out.println(longestPalindrome("abccba")); // Output: "bab" or "aba"
    }
    public static  String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        boolean[][] dp = new boolean[n][n];
        String ans = s.substring(0, 1);

        // Initialize base cases
        for (int i = 0; i < n; i++) {
            dp[i][i] = true; // Single-character substrings
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true; // Two-character substrings
                ans = s.substring(i, i + 2); // Update palindrome
            }
        }

        // Fill DP table for substrings of length >= 3
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    ans = s.substring(i, j + 1);
                }
            }
        }

        return ans;
    }
}

