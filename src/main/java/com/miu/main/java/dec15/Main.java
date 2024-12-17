package com.miu.main.java.dec15;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

//        int[] arr = {12, 11, 13, 5, 6, 7};
//
//        mergeSort(arr, 0, arr.length - 1);
//
//        System.out.println(Arrays.toString(arr));
        //While merging two sorted arrays during the merge step of merge sort,
        // how do you calculate the number of inversions between the two arrays?
        // Explain why and when multiple inversions can occur in one comparison.
        // Provide an example with two sorted arrays and count the total number of inversions.

        int[] main = {1, 3, 5, 2, 4, 6};
        // (3,2) (5,2)(5,4)
        int[] left = {1, 3, 5};
        int[] right = {2, 4, 6};

        System.out.println(numberOfInversion(left, right));

    }

    private static int numberOfInversion(int[] left, int[] right) {


        int m = left.length;
        int n = right.length;

        int i = 0;
        int j = 0;
        int k = 0;

        int inv = 0;
        while (i < n && j < m) {
            if (left[i] <= right[j]) {

                i++;
            } else {

                j++;
                inv += m - i;
            }

        }
        return inv;

    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            mergeLeftAndRight(arr, left, mid, right);

        }

    }

    private static void mergeLeftAndRight(int[] arr, int left, int mid, int right) {
        int m = mid - left + 1;
        int n = right - mid;

        int[] leftArray = new int[m];

        int[] rightArray = new int[n];

        for (int i = 0; i < m; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int i = 0; i < n; i++) {
            rightArray[i] = arr[mid + 1 + i];
        }
        int i = 0;
        int j = 0;
        int k = left;

        while (i < m && j < n) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
                k++;
            } else {
                arr[k] = rightArray[j];
                j++;
                k++;
            }
        }

        while (i < m) {
            arr[k] = leftArray[i];
            k++;
            i++;
        }
        while (j < n) {
            arr[k] = rightArray[j];
            k++;
            j++;
        }

    }
}
