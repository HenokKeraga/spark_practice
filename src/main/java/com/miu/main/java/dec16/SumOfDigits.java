package com.miu.main.java.dec16;

public class SumOfDigits {
    public static void main(String[] args) {

        System.out.println(sumDigits(253));
    }

    private static int sumDigits(int n) {
      if(n==0){
          return 0;
      }

        return (n % 10) + sumDigits(n/10);
    }
}
