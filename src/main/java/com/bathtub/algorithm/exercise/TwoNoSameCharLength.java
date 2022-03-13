package com.bathtub.algorithm.exercise;

import java.util.Scanner;

/**
 * 给定一个元素类型为小写字符串的数组
 * 请计算两个没有相同字符的元素长度乘积的最大值
 * 如果没有符合条件的两个元素返回0
 *
 * 输入描述
 *   输入为一个半角逗号分割的小写字符串数组
 *   2<= 数组长度 <=100
 *   0< 字符串长度 <=50
 * 输出描述
 *   两个没有相同字符的元素长度乘积的最大值
 *
 * 示例一
 *   输入
 *     iwdvpbn,hk,iuop,iikd,kadgpf
 *   输出
 *     14
 *   说明
 *    数组中有5个元组  第一个和第二个元素没有相同字符
 *    满足条件 输出7*2=14
 * ————————————————
 * 版权声明：本文为CSDN博主「羊族的希望」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/weixin_41010318/article/details/120820099
 * @author 17031612
 * @date 2022/1/18
 */
public class TwoNoSameCharLength {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(",");
        int[][] dicArr = new int[arr.length][26];
        for (int i = 0; i < arr.length; i++) {
            int[] dic = new int[26];
            dicArr[i] = dic;
            char[] arri = arr[i].toCharArray();
            for (char c : arri) {
                dic[c - 'a'] = 1;
            }
        }

        int max = 0;
        for (int m = 0; m < arr.length; m++) {
            for (int n = m+1; n < arr.length; n++) {
                if (checkDiff(dicArr[m], dicArr[n])) {
                    max = Math.max(max, arr[m].length()*arr[n].length());
                }
            }
        }
        System.out.println(max);
    }

    private static boolean checkDiff(int[] a, int[] b) {
        for (int i = 0;i < 26; i++) {
            if (a[i] == 1 && b[i] == 1)
                return false;
        }
        return true;
    }
}
