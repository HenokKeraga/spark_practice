package com.miu.main.java.dec5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutations {
    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> result = new ArrayList<>();
        directedPermutations(0, A, result);
        return result;
    }

    private static void directedPermutations(int i, List<Integer> A, List<List<Integer>> result) {
        if (i == A.size() - 1) {
            // Add a new copy of A to the result list
            result.add(new ArrayList<>(A));
            return;
        }

        // Try every possibility for A[i].
        for (int j = i; j < A.size(); ++j) {
            Collections.swap(A, i, j); // Swap A[i] and A[j]
            directedPermutations(i + 1, A, result); // Generate all permutations for the sublist
            Collections.swap(A, i, j); // Swap back to restore the original order
        }
    }

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<List<Integer>> result = permutations2(input);
        System.out.println(result);
    }

    public static List<List<Integer>> permutations2(List<Integer> input) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[input.size()];
        permutationHelper(0, input, result, new ArrayList<>());

        return result;
    }


    private static void permutationHelper(int i, List<Integer> input, List<List<Integer>> result, List<Integer> temp) {
        if (i == input.size() - 1) {

            //  result.add(new ArrayList<>(temp));
            result.add(new ArrayList<>(input));
            return;
        }


        for (int j = i; j < input.size(); j++) {
//            if (!used[i]) {

            //     temp.add(input.get(i));
            Collections.swap(input, i, j);

            permutationHelper(i + 1, input, result, temp);
            Collections.swap(input, i, j);
            // temp.remove(temp.size() - 1);

//            }

        }
    }


}

