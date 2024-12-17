package com.miu.main.java.dec16;

public class RobCuttingProblem {
    public static void main(String[] args) {

//        System.out.println(maxNumberCut(5, 2, 5, 1));
//        System.out.println(maxNumberCut(5, 4, 2, 6));
        System.out.println(maxNumberCut(9, 2, 2, 2));
    }

    private static int maxNumberCut(int n, int a, int b, int c) {

        if (n == 0) {
            return 0;
        }
        if (n < 0) {
            return -1;
        }


        int ca = maxNumberCut(n - a, a, b, c);
        int cb = maxNumberCut(n - b, a, b, c);
        int cc = maxNumberCut(n - c, a, b, c);
        int result = Math.max(Math.max(ca, cb), cc);

        if (result != -1) {
            result = result + 1;
        }

        return result;
    }
}
