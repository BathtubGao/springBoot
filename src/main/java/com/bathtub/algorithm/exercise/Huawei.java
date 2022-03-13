package com.bathtub.algorithm.exercise;


import java.util.Scanner;

public class Huawei {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        String[] input = sc.nextLine().split(" ");
        int[] ints = new int[num];
        for (int i = 0; i < num; i++) {
            ints[i] = Integer.parseInt(input[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0;  i < num-1; i++) {
            if (ints[i+1] > ints[i]) {
                sb.append(i+1).append(" ");
            } else {
                int end = i+2;
                while (end < num) {
                    if (ints[end] > ints[i]) {
                        sb.append(end).append(" ");
                        break;
                    }
                    end++;
                }
                if (end >= num) {
                    sb.append(0).append(" ");
                }
            }
        }
        sb.append(0).append(" ");
        System.out.println(sb);
    }
}
