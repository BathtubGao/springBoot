package com.bathtub.algorithm.exercise;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 给定一个数组，求是否存在满足A=B+2C等式的三个元素A、B、C
 * @author 17031612
 * @date 2022/1/16
 */
public class CheckArr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputs = scanner.nextLine().split(" ");
        Set<Integer> set = new HashSet<>();
        for (String input : inputs) {
            set.add(Integer.parseInt(input));
        }
        if (set.size() < 3)
            return;
        hasEqu(set);
    }

    private static boolean hasEqu(Set<Integer> set) {
        for (int a : set) {
            for (Integer b : set) {
                if (a <= b)
                    continue;
                int lest = a - b;
                if ((lest & 1) != 0)
                    continue;
                int c = lest >> 1;
                if (set.contains(c)) {
                    System.out.println(a + "=" + b + "+2*" + c);
                    return true;
                }
            }
        }
        return false;
    }
}
