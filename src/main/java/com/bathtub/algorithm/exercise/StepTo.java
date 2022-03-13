package com.bathtub.algorithm.exercise;

import java.util.Scanner;

/**
 * 一串数字，从第一个开始走，需要几步能够达到最后一个数，返回步数，或者是不能达到，返回-1；
 * 第一步的步长最大为len/2，后面每一步的步长都是它当前所在位置的数字的大小。
 * @author 17031612
 * @date 2022/1/17
 */
public class StepTo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputs = scanner.nextLine().split(" ");
        int[] ints = new int[inputs.length];
        for (int i = 0;i < inputs.length; i++) {
            ints[i] = Integer.parseInt(inputs[i]);
        }
        int step = 1;
        int index = ints.length >> 1;
        int indexV = ints[index];
        ints[index] = ints.length;
        while (indexV < ints.length) {
            if (indexV == ints.length-1) {
                System.out.println(step);
                return;
            }
            step++;
            index = indexV;
            indexV = ints[indexV];
            ints[index] = ints.length;
        }
        System.out.println(-1);
    }
}
