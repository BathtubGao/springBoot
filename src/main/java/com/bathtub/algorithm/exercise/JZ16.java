package com.bathtub.algorithm.exercise;

/**
 * 实现函数 double Power(double base, int exponent)，求base的exponent次方。
 * https://www.nowcoder.com/practice/1a834e5e3e1a4b7ba251417554e07c00
 */
public class JZ16 {
    public double Power(double base, int exponent) {
        if (base == 0)
            return 0;
        if (exponent == 0)
            return 1;
        double res = 1;
        int exp = Math.abs(exponent);
        while (exp-- > 0) {
            res *= base;
        }
        if (exponent < 0)
            return 1/res;
        else
            return res;
    }
}
