package com.miu.main.java.dec16;

import java.util.ArrayList;
import java.util.List;

public class TowerOfHanoi {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        toh(3, 'a', 'b', 'c', list);

        list.forEach(System.out::println);
    }

    public static void toh(int n, char a, char b, char c, List<String> result) {
        if (n < 1) {
            return;
        }

        toh(n - 1, a, c, b,result);
        result.add("move " + n + " from " + a + " to " + c);
        toh(n - 1, b, a, c,result);


    }
}
