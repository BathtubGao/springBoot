package com.bathtub.algorithm.exercise;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/05182d328eb848dda7fdd5e029a56da9
 * 来源：牛客网
 *
 * 实现删除字符串中出现次数最少的字符，若多个字符出现次数一样，则都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 * 注意每个输入文件有多组输入，即多个字符串用回车隔开
 *
 * 数据范围：输入的字符串长度满足  ，保证输入的字符串中仅出现小写字母
 * @author 17031612
 * @date 2022/1/17
 */
public class DelLeastChar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] chars;
        int[] ints;
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            chars = str.toCharArray();
            ints = new int[26];
            for (char c : chars) {
                int index = c-'a';
                ints[index] += 1;

            }
            int min = chars.length;
            for (int i = 0; i < ints.length; i++) {
                if (ints[i] != 0) {
                    min = Math.min(min, ints[i]);
                }
            }
            for (int i = 0; i < ints.length; i++) {
                if (ints[i] == min) {
                    char c = (char)('a' + i);
                    str = str.replaceAll(String.valueOf(c), "");
                }
            }
            System.out.println(str);
        }
    }
}
