package com.bathtub.algorithm.exercise;

import java.util.Scanner;

public class Huawei2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (null == input || input.length() == 0 || "null".equals(input)) {
            System.out.println(0);
            return;
        }
        String[] inputs = input.split(",");
        int size = inputs.length;
        int[] cars = new int[size];
        for (int i = 0; i < size; i++) {
            cars[i] = Integer.parseInt(inputs[i]);
        }
        int begin = -1;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            if (cars[i] == 0) {
                int len = i - begin - 1;
                if (len > 0) {
                    sum += (--len/3) + 1;
                }
                begin = i;
            }
        }
        if (cars[size-1] == 1) {
            int len = size - begin - 1;
            if (len > 0) {
                sum += (--len/3) + 1;
            }
        }
        System.out.println(sum);
    }
}
