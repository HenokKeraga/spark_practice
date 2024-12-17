package com.miu.main.java.dec14;

public class Palindrome {

    public static void main(String[] args) {
        String word = "aba";
        System.out.println(isPalindrome(word, 0, word.length() - 1));
    }

    private static boolean isPalindrome(String word, int start, int end) {
        if (start >= end) {
            return true;
        }
        //


        return word.charAt(start) == word.charAt(end) && isPalindrome(word, start + 1, end - 1);
    }


}
