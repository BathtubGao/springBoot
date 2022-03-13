package com.bathtub.algorithm.exercise;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class ReverseInt {
    public static int reverse(int x) {
        if (x == 0)
            return 0;
        long res = 0;
        while(x != 0) {
            res = res * 10 + (x % 10) ;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
                return 0;
            x = x / 10;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-123));
    }
}
