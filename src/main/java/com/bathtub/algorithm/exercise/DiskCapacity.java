package com.bathtub.algorithm.exercise;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 磁盘容量大小排序
 * 磁盘的容量单位有M、G、T，其关系为 1T = 1000G、1G = 1000M，如样例所示先输入磁盘的个数，
 * 再依次输入磁盘的容量大小，然后按照从小到大的顺序对磁盘容量进行排序并输出。
 * @author 17031612
 * @date 2022/1/10
 */
public class DiskCapacity {
    public static void main(String[] args) {
//        System.out.println(cmp("1.234G", "1.334G"));
//        System.out.println(changeStr("0.1234M", 3));
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] strArr = scanner.nextLine().split(" ");
            int num = Integer.parseInt(strArr[0]);
            String[] tarArr = Arrays.copyOfRange(strArr, 1, num+1);
            Arrays.sort(tarArr, DiskCapacity::cmp);
            System.out.println(Arrays.toString(tarArr));
        }
    }

    private static int cmp(String str1, String str2) {
        String change1 = changeStr(str1);
        String change2 = changeStr(str2);
        return cmpChange(change1, change2);
    }

    private static int cmpChange(String str1, String str2) {
        int index1 = str1.indexOf(".");
        String base1;
        String left1;
        if (index1 > 0) {
            base1 = str1.substring(0, index1);
            left1 = str1.substring(index1+1);
        } else {
            base1 = str1;
            left1 = "";
        }
        int index2 = str2.indexOf(".");
        String base2;
        String left2;
        if (index2 > 0) {
            base2 = str2.substring(0, index2);
            left2 = str2.substring(index1+1);
        } else {
            base2 = str2;
            left2 = "";
        }
        if (base1.length() > base2.length())
            return 1;
        if (base1.length() < base2.length())
            return -1;
        int baseCom = base1.compareTo(base2);
        if (baseCom != 0) {
            return baseCom;
        }
        if (left1.length() > left2.length())
            return 1;
        if (left1.length() < left2.length())
            return -1;
        return left1.compareTo(left2);
    }


    private static String changeStr(String str) {
        String unit = str.substring(str.length()-1);
        str = str.substring(0, str.length()-1);
        int step = 0;
        if ("G".equals(unit))
            step = 3;
        if ("T".equals(unit))
            step = 6;
        if (step == 0)
            return str;

        int i = 0;
        while (i < step) {
            str = str + "0";
            i++;
        }
        int index = str.indexOf(".");
        if (index < 0) {
            return str;
        }

        char[] chars = str.toCharArray();
        int spaceIndex = 0;
         for (char c : chars) {
            if (c != '0') {
                break;
            }
            spaceIndex++;
        }

        String base = str.substring(spaceIndex, index);
        String left = str.substring(index + 1);
        return base + left.substring(0, step) + "." + left.substring(step);
    }
}
