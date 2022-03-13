package com.bathtub.algorithm.exercise;

import java.util.*;

/**
  * 输入描述：以逗号为分隔，描述3个int类型整数的字符串。
  * 输出描述：这几个数字可拼成的数字从小到大排列位于第N（N为输入数字中最大的数字）位置的数字，如果输入的数字为负数或者不是合法的字符串或者有重复，返回-1。
  * 注意：
  * （1）屏幕如果给出的是“2”，大家可把它当作“2”，也可把它当作“5”来拼接数字；同理，如果屏幕给的是“5”，大家可把它当作“5”，也可以把它当作“2”来拼接数字，但屏幕不能同时给出“2”和“5”。
  * （2）屏幕如果给出的是“6”，大家可把它当作“6”，也可把它当作“9”来拼接数字；同理，如果屏幕给的是“9”，大家可把它当作“9”，也可以把它当作“6”来拼接数字，但屏幕不能同时给出“6”和“9”。
  * 如：给出：1，4，8，则可以拼成的数字为：
  * 1，4，8，14，18，41，48，81，84，148，184，418，481，814，841
  * 第N（即8）个的数字为81.
  * 原文链接：https://blog.csdn.net/Jocelyn92/article/details/69808047
  * @author 17031612
  * @date 2021/12/23
  */
public class ThreeNumSplicing {

    public static void main(String[] args) {
        System.out.println(threeNumSplicing("1,3,9", 15));
    }

    public static int threeNumSplicing(String intStr, int limit) {
        if (null == intStr || intStr.length() == 0) {
            return -1;
        }
        String[] arr = intStr.split(",");
        if (arr.length > 3) {
            return -1;
        }
        int[] ints = new int[10];
        for (String str : arr) {
            int i = Integer.parseInt(str);
            ints[i] = 1;
            if (i == 2) {
                ints[5] = 1;
            }
            if (i == 5) {
                ints[2] = 1;
            }
            if (i == 6) {
                ints[9] = 1;
            }
            if (i == 9) {
                ints[6] = 1;
            }
        }
        List<String> base = new ArrayList<>();
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] == 1) {
                base.add(String.valueOf(i));
            }
        }
        if (base.size() >= limit) {
            return Integer.parseInt(base.get(limit-1));
        }
        List<String> pool = new ArrayList<>(base);
        int index = base.size()+1;
        int poolBegin = 0;
        int poolEnd = pool.size();
        while(true) {
            for(String baseStr : base) {
                for(int i=poolBegin; i < poolEnd; i++) {
                    String poolStr = pool.get(i);
                    String tempStr = baseStr+poolStr;
                    if (!checkValid(baseStr, poolStr)) continue;
                    if (index == limit)  return Integer.parseInt(tempStr);
                    index++;
                    pool.add(tempStr);
                }
            }
            poolBegin = poolEnd;
            poolEnd = pool.size();

        }
    }

    private static boolean checkValid(String baseStr, String poolStr) {
        if (poolStr.contains(baseStr)) return false;
        if ("2".equals(baseStr) && poolStr.contains("5")) return false;
        if ("5".equals(baseStr) && poolStr.contains("2")) return false;
        if ("6".equals(baseStr) && poolStr.contains("9")) return false;
        if ("9".equals(baseStr) && poolStr.contains("6")) return false;
        return true;
    }

}
