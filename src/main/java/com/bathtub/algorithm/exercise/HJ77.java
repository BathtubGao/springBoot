package com.bathtub.algorithm.exercise;

import java.util.*;

/**
 * https://www.nowcoder.com/practice/97ba57c35e9f4749826dc3befaeae109?tpId=37&&tqId=21300&rp=1&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 */
public class HJ77 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int num = Integer.parseInt(scanner.nextLine());
            String[] inputs = scanner.nextLine().split(" ");
        }
    }

    /**
     *火车进站后有两种选择，直接出站 和 不出站等待下一列火车进站
     *我是先处理火车直接出站，再处理等待的，直到所有的火车都是等待状态，全部结束
     *只要有火车出站，就进入递归判断下一列火车，然后递归方法结束后重置in和end数组
     */
}
