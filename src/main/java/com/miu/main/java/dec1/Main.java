package com.miu.main.java.dec1;

public class Main {

    public static void main(String[] args) {


//        System.out.println(getMinDiff(new int[]{1, 8, 10, 6, 4, 6, 9, 1}, 7));
        // System.out.println(getMinDiff(new int[]{1, 1, 4, 6, 6, 8, 9, 10}, 7));
        int[] data = new int[]{1,2,5,2,7,9};
        System.out.println(getMaxFromArray(data,data.length -1));
//        System.out.println(getMinDiff(new int[]{1, 5, 8, 10}, 2));


    }

    private static int getMaxFromArray(int[] data, int n) {




        return getMaxFromArrayHelper(data,n,Integer.MIN_VALUE,Integer.MAX_VALUE,0);

    }

    private static int getMaxFromArrayHelper(int[] data, int n, int maxTillNow,int minTillNow, int k) {
        if(n < 0){
            return maxTillNow -minTillNow;
        }

        int currentValue =  data[n] + k;

        int option1 = getMaxFromArrayHelper(data,n-1,Math.max(maxTillNow,currentValue),Math.min(minTillNow,currentValue),k);

        int decreaseValue =  data[n] - k;

        int option2 = getMaxFromArrayHelper(data,n-1,Math.max(maxTillNow,decreaseValue),Math.min(minTillNow,decreaseValue),k);

        return Math.min(option1,option2);
//        return option2;
    }


}
