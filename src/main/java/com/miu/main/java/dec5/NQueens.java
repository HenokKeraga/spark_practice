package com.miu.main.java.dec5;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

//    public static List<List<Integer>> nQueens(int n) {
//        List<List<Integer>> result = new ArrayList<>();
//        solveNQueens(n, 0, new ArrayList<>(), result);
//        return result;
//    }
//
//    private static void solveNQueens(int n, int row, List<Integer> colPlacement, List<List<Integer>> result) {
//        if (row == n) {
//            // All queens are legally placed.
//            result.add(new ArrayList<>(colPlacement));
//        } else {
//            for (int col = 0; col < n; ++col) {
//                colPlacement.add(col); // Place the queen at (row, col)
//                if (isValid(colPlacement)) {
//                    solveNQueens(n, row + 1, colPlacement, result);
//                }
//                colPlacement.remove(colPlacement.size() - 1); // Backtrack
//            }
//        }
//    }
//
//    // Check if the current placement is valid
//    private static boolean isValid(List<Integer> colPlacement) {
//        int rowID = colPlacement.size() - 1; // Get the row of the last placed queen
//        for (int i = 0; i < rowID; ++i) {
//            int diff = Math.abs(colPlacement.get(i) - colPlacement.get(rowID));
//            // Check if they are in the same column or on the same diagonal
//            if (diff == 0 || diff == rowID - i) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static void main(String[] args) {
        int n = 4; // Change this value to test different board sizes
        List<List<Integer>> solutions = nQueens(n);

        System.out.println("Number of solutions for " + n + "-Queens: " + solutions.size());
        for (List<Integer> solution : solutions) {
            System.out.println(solution);
        }
    }

    private static List<List<Integer>> nQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();

        placementQueenHelper(n, result, new ArrayList<>(), 0);

        return result;
    }

    private static void placementQueenHelper(int n, List<List<Integer>> result, List<Integer> temp, int row) {
        if (temp.size() == n) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < n; i++) {
            temp.add(i);
            if (isAllowedToPut(temp)) {
                placementQueenHelper(n, result, temp, row + 1);
            }
            temp.remove(temp.size() - 1);
        }

    }

    private static boolean isAllowedToPut(List<Integer> temp) {
        int lastRow = temp.size() - 1; // Row of the last placed queen
        int lastColumn = temp.get(lastRow); // Column of the last placed queen

        for (int i = 0; i < lastRow; i++) {
            int diff = Math.abs(temp.get(i) - lastColumn);
            // Check for column and diagonal conflicts
            if (diff == 0 || diff == lastRow - i) {
                return false;
            }
        }

        return true;
    }


}

