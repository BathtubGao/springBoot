package com.bathtub.algorithm.exercise;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/6d9d69e3898f45169a441632b325c7b4?tpId=37&&tqId=21247&rp=1&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 */
public class HJ24 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int num = Integer.parseInt(scanner.nextLine());
            String[] strs = scanner.nextLine().split(" ");
            int[] inputs = new int[num];
            for (int i = 0; i< num; i++) {
                inputs[i] = Integer.parseInt(strs[i]);
            }
            int[] ldp = new int[num]; // 当前节点的从左递增子序列长度
            for (int i = 1; i < num; i++) {
               for (int j = 0; j < i; j++) {
                   if (inputs[i] > inputs[j]) {
                       ldp[i] = Math.max(ldp[i], ldp[j] + 1);
                   }
               }
            }
            int[] rdp = new int[num]; // 当前节点的从右递增子序列长度
            for (int i = num-2; i >= 0; i--) {
                for (int j = num-1; j > i; j--) {
                    if (inputs[i] > inputs[j]) {
                        rdp[i] = Math.max(rdp[i], rdp[j] + 1);
                    }
                }
            }
            int max = 0;
            for (int i = 0; i < num; i++) {
                max = Math.max(ldp[i]+rdp[i], max);
            }
            System.out.println(num - max - 1);
        }

    }
}
