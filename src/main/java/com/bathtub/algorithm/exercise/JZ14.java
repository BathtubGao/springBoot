package com.bathtub.algorithm.exercise;

/**
 * https://www.nowcoder.com/practice/57d85990ba5b440ab888fc72b0751bf8
 */
public class JZ14 {
    public static int cutRope(int target) {
        if (target <= 4) {
            return target;
        }
        int num = target / 3;
        int les = target % 3;
        if (les == 0) {
            return (int) Math.pow(3, num);
        } else if (les == 1) {
            return (int) (Math.pow(3, num-1) * 2 * 2);
        } else {
            return (int) (Math.pow(3, num)  * 2);
        }
    }
}
