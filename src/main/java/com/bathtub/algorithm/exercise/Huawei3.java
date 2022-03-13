package com.bathtub.algorithm.exercise;

import java.util.Scanner;

public class Huawei3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        int index = str1.indexOf(" ");
        int width = Integer.parseInt(str1.substring(0, index));
        int height = Integer.parseInt(str1.substring(index + 1));
        int[][] ints = new int[height][width];
        String[] step = sc.nextLine().split(" ");
        int[] stepInt = new int[step.length];
        for (int i = 0; i < step.length; i++) {
            stepInt[i] = Integer.parseInt(step[i]);
        }
        for (int i = 0; i < step.length; i++) {
            if (!check(ints, stepInt, i, width, height)) {
                return;
            }
        }
        System.out.println("0,draw");
    }


    private static boolean check(int[][] ints, int[] stepInt, int stepIndex, int width, int height) {
        int index = stepInt[stepIndex]; // 从1开始
        if (index <= 0 || index > width || index > height) {
            System.out.println(stepIndex + 1 + ",error");
            return false;
        }
        int play = (stepIndex & 1) + 1; // 2取余，1 为红，2为蓝
        // 落子
        int rindex = index - 1;
        int heiIndex = -1;
        for (int i = height - 1; i >= 0; i--) {
            if (ints[i][rindex] == 0) {
                ints[i][rindex] = play;
                heiIndex = i;
                break;
            }
        }
        if (heiIndex < 0) {
            System.out.println(stepIndex + 1 + ",error");
            return false;
        }
        if (stepIndex < 6) {
            // 不满足检查落子数
            return true;
        }
        // 检查获胜
        // 向上向下
        int sum = 1;
        int up = heiIndex - 1;
        while (up >= 0 && ints[up][rindex] == play) {
            sum++;
            up--;
        }
        int down = heiIndex + 1;
        while (down < height && ints[down][rindex] == play) {
            sum++;
            down++;
        }
        if (sum == 4) {
            System.out.println(stepIndex + 1 + "," + (play == 1 ? "red" : "blue"));
            return false;
        }

        // 水平检查
        sum = 1;
        int left = rindex - 1;
        while (left >= 0 && ints[heiIndex][left] == play) {
            sum++;
            left--;
        }
        int right = rindex + 1;
        while (down < width && ints[heiIndex][right] == play) {
            sum++;
            right++;
        }
        if (sum == 4) {
            System.out.println(stepIndex + 1 + "," + (play == 1 ? "red" : "blue"));
            return false;
        }

        // 斜
        sum = 1;
        int xuindex1 = heiIndex - 1;
        int xuindex2 = rindex - 1;
        while (xuindex1 >= 0 && xuindex2 >= 0 && ints[xuindex1][xuindex2] == play) {
            sum++;
            xuindex1--;
            xuindex2--;
        }
        int xdindex1 = heiIndex + 1;
        int xdindex2 = rindex + 1;
        while (xdindex1 < height && xdindex2 < width && ints[xdindex1][xdindex2] == play) {
            sum++;
            xdindex1++;
            xuindex2++;
        }
        if (sum == 4) {
            System.out.println(stepIndex + 1 + "," + (play == 1 ? "red" : "blue"));
            return false;
        }

        sum = 1;
        int uindex1 = heiIndex - 1;
        int uindex2 = rindex + 1;
        while (uindex1 >= 0 && uindex2 < width && ints[uindex1][uindex2] == play) {
            sum++;
            uindex1--;
            uindex2++;
        }
        int dindex1 = heiIndex + 1;
        int dindex2 = rindex - 1;
        while (dindex1 < height && dindex2 >= 0 && ints[dindex1][dindex2] == play) {
            sum++;
            dindex1++;
            dindex2--;
        }
        if (sum == 4) {
            System.out.println(stepIndex + 1 + "," + (play == 1 ? "red" : "blue"));
            return false;
        }
        return true;
    }
}
