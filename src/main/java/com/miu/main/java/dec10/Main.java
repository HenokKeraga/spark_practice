package com.miu.main.java.dec10;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int k = 2; // 1 to k steps
        int[] steps = new int[k + 1]; // Adjust array size for 1-based indexing

        // Define step values
        steps[1] = 1;
        steps[2] = 2;

        int d = 3; // Destination

        System.out.println(numberOfWaysToReachDestination2(steps, 2, d));

    }

//    public static int numberOfWaysToReachDestination(int[] steps, int index, int d) {
//        if (d == 0) {
//            return 1;
//        }
//        if (index <= 0 || d < 0) {
//            return 0;
//        }
//
//
//        int right = numberOfWaysToReachDestination(steps, index, d - steps[index]);
//        int left = numberOfWaysToReachDestination(steps,index-1,d);
//
//        return left + right;
//
//    }
public static int numberOfWaysToReachDestination2(int[] steps, int index, int d) {
    if (d == 0) {
        // Reached the destination
        return 1;
    }
    if (d < 0) {
        // Overshot the destination
        return 0;
    }

    if (index == 0) {
        // No more steps to use
        return 0;
    }

    // Case 1: Use the current step
    int useCurrentStep = numberOfWaysToReachDestination2(steps, index, d - steps[index]);

    // Case 2: Skip the current step
    int skipCurrentStep = numberOfWaysToReachDestination2(steps, index - 1, d);

    // Total ways is the sum of both cases
    return useCurrentStep + skipCurrentStep;
}


}
