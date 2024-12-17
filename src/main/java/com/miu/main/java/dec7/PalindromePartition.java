package com.miu.main.java.dec7;

public class PalindromePartition {

    public static void main(String[] args) {

        System.out.println(palindromeP("geek"));
    }

    private static int palindromeP(String str) {

        return palindromePHelper(str, 0, str.length());
    }

    private static int palindromePHelper(String str, int i, int j) {


        if (isPalindrome(str,i,j)) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
           result =  Math.min(result, 1 + palindromePHelper(str, i, k) + palindromePHelper(str, k + 1, j));

        }

        return result;
    }

    private static boolean isPalindrome(String str,int i, int j) {
        j=j-1;
        while (i<j) {
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

//    public static boolean isPalindrome(String str){
//
//
//
//
//    }
}
