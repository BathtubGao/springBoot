package com.bathtub.algorithm.exercise;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 100个人围成一个圈（编号从1到100），然后报数，报到3的下去，然后从4开始继续从1开始报数，报到3的下去，依此类推，最后剩下人的编号是？
 * @author 17031612
 * @date 2022/1/18
 */
public class NumberOff {
    public static void main(String[] args) {
        numberOff();
    }

    private static void numberOff() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                System.out.println(i);
                continue;
            }
            queue.add(i);
        }
        int index = 2;
        while (queue.size() > 1) {
            int e = queue.poll();
            if (index != 3) {
                queue.add(e);
                index++;
            } else {
                System.out.println(e);
                index = 1;
            }
        }
        System.out.println(queue.element());
    }
}
