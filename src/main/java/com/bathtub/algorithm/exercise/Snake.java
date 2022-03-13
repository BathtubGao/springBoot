package com.bathtub.algorithm.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 第一行输入U D L R G
 * 分别代表向上、下、左、右、前进五种动作，上下左右贪吃蛇不动，而是转换蛇头方向，G才移动。
 *
 * 第二行输入游戏地图的长和宽
 * 例如 3 2
 * 代表三行两列的地图
 *
 * 第三行输入此游戏地图各个位置的内容，即输入一个二维数组。数组中的元素有：H蛇头、E空位、F食物。
 * 规则：就跟平常玩的一样，吃到食物蛇长加1，撞到自己或墙壁游戏结束。
 * 根据不同的输入返回对应的蛇长。
 */
public class Snake {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] action = scanner.nextLine().split(" ");
        String line2 = scanner.nextLine();
        int line2Indedx = line2.indexOf(" ");
        int length = Integer.parseInt(line2.substring(0, line2Indedx));
        int width = Integer.parseInt(line2.substring(line2Indedx + 1));
        String[] graphStr = scanner.nextLine().split(" ");
        String[][] graph = new String[length][width];
        int i = 0;
        int j = 0;
        int[] head = new int[2];
        Queue<int[]> snake = new LinkedList<>();
        for (String str : graphStr) {
            graph[j][i] = str;
            if ("H".equals(str)) {
                snake.offer(new int[]{j,i});
                head[0] = j;
                head[1] = i;
            }
            i++;
            if (i == width) {
                j++;
                i = 0;
            }
        }
        int real = 0;
        for (int r = 1; r < action.length; r++) {
            if (!"G".equals(action[r])) {
                real = r;
            } else {
                switch (action[real]) {
                    case "U" :
                        head[0] = head[0] - 1;
                        break;
                    case "D":
                        head[0] = head[0] + 1;
                        break;
                    case "L":
                        head[1] = head[1] - 1;
                        break;
                    case "R":
                        head[1] = head[1] + 1;
                        break;
                    default:
                        System.out.println(snake.size());
                        return;
                }
                // 边界检查
                if (head[0] < 0 || head[1] < 0 || head[0] >= length || head[1] >= width) {
                    System.out.println(snake.size());
                    return;
                }
                // 自身检查
                for (int[] self : snake) {
                    if (head[0] == self[0] && head[1] == self[1]) {
                        System.out.println(snake.size());
                        return;
                    }
                }
                snake.add(new int[]{head[0],head[1]});
                if (!"F".equals(graph[head[0]][head[1]])) {
                    snake.poll(); // 非食物移除
                }
            }
        }
        System.out.println(snake.size());
    }
}
