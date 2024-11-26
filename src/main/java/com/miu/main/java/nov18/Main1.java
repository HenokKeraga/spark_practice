package com.miu.main.java.nov18;

import java.util.HashMap;
import java.util.Map;

public class Main1 {
    public static void main(String[] args) {
        //

//        String string = "Counting duplicate characters: Write a program that counts duplicate characters in a given string.";
//
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < string.length(); i++) {
//            char c = string.charAt(i);
//            map.put(c, map.getOrDefault(c, 0) + 1);
//
//        }

//        System.out.println(map);

        //
        System.out.println(reverseExponentiation(2));

    }
    public static int reverseExponentiation(int n) {
        // code here

        int m = n;
        int r =0;
        while(m>0){
            r = r*10+m%10;

            m=m/10;
        }
        int ans=1;

        for(int i=0;i<r;i++){
            ans*=n;
        }

        return ans;
    }
}
