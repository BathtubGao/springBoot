package com.bathtub.algorithm.exercise;

import java.util.Scanner;

/**
 * 输入包含多组测试数据。
 * 对于每组测试数据：
 * 第一行：n --- 砝码数(范围[1,10])
 * 第二行：m1 m2 m3 ... mn --- 每个砝码的重量(范围[1,2000])
 * 第三行：x1 x2 x3 .... xn --- 每个砝码的数量(范围[1,6])
 * 输出描述：
 * 利用给定的砝码可以称出的不同的重量数
 * @author 17031612
 * @date 2022/1/13
 */
public class HJ41 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int amount = Integer.parseInt(scanner.nextLine());
            String[] weightStr = scanner.nextLine().split(" ");
            String[] numStr = scanner.nextLine().split(" ");
            int[] weightArr = new int[amount];
            int[] numArr = new int[amount];
            int sum = 0;
            int index = 0;
            while(index < amount) {
                weightArr[index] = Integer.parseInt(weightStr[index]);
                numArr[index] = Integer.parseInt(numStr[index]);
                sum += weightArr[index] * numArr[index];
                index++;
            }
            boolean[] result = new boolean[sum+1];
            result[0] = result[sum] = true;
            int max = 0;
            for (int i = 0; i < amount; i++) {
                for (int m = 0; m < numArr[i]; m++) {
                    max += weightArr[i];
                    result[max] = true;
                    for (int n = max-1; n >= weightArr[i]; n--) {
                        if (result[n-weightArr[i]] && !result[n])
                            result[n] = true;
                    }
                }
            }
            int count = 0;
            for (boolean one : result) {
                if (one)
                    count++;
            }
            System.out.println(count);
        }
    }
}
