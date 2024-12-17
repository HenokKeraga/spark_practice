package com.miu.main.java.dec15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {

        mergeOverlap(new int[][]{{1, 3}, {2, 4}, {6, 8}, {9, 10}})
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    public static List<int[]> mergeOverlap(int[][] arr) {
        // Code here // Code here

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i][1]);
//            System.out.println(arr[i+1][0]);

            if ( i+1 < arr.length && arr[i + 1][0] < arr[i][1] ) {
                result.add(new int[]{arr[i][0], arr[i + 1][1]});
                i++;
            } else {
                result.add(new int[]{arr[i][0], arr[i][1]});
            }

        }


        return result;
    }
}
