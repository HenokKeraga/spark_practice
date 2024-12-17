package com.miu.main.java.dec16;

import java.util.ArrayList;
import java.util.List;

public class GenerateSubset {
    public static void main(String[] args) {
        String string = "AB";
        List<String> result = new ArrayList<>();

        subSet(string, string.length() - 1, result, "");
        System.out.println(result);
    }

    private static void subSet(String string, int n, List<String> result, String current) {

        if (n < 0) {
            result.add(current);
            return;
        }

        subSet(string, n - 1, result, current);
        subSet(string, n - 1, result,  string.charAt(n) + current );

    }

}
