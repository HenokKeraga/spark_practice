package com.miu.main.java.dec10;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println(db(new int[]{3, 4, 2, 8, 10}));
    }


    public static int db(int[] arr) {



        return 0;
    }

    public static int bsa(int[] arr) {

        return 0;
    }


    private static int computeLongestIncreasingSubsequence(int[] arr) {

        return helperI(arr, arr.length - 1, arr.length);
    }

    private static int helperI(int[] arr, int currentIndex, int nextIndex) {

        if (currentIndex < 0) {
            return 0;
        }

        int exclude = helperI(arr, currentIndex - 1, nextIndex);

        int include = 0;

        if (nextIndex == arr.length || arr[currentIndex] < arr[nextIndex]) {
            include = 1 + helperI(arr, currentIndex - 1, currentIndex);
        }


        return Math.max(include, exclude);
    }
}
