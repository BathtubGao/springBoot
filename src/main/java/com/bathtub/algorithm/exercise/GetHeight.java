package com.bathtub.algorithm.exercise;


import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * 给定一组随机数，拆分成两个数组，两个数组中数值的和值相等，求最大的和值是多少
 * [1,3,4,7,9,12] -> 16
 * @author 17031612
 * @date 2020/4/16
 */
public class GetHeight {
    public static void main(String[] args) {
//        int[] target = {12,4,1,2,7,3,9,5};
        int[] target = {5,1,2, 4, 9, 7};
        System.out.println(getHeightNew(target));
    }



    /**
     * @author 17031612
     * @date 2021/12/30
     */
    private static int getHeightNew(int[] tar) {
        int max = 0;
        int sum = 0;
        for (int i = 0; i < tar.length; i++) {
            if (tar[i] > max) {
                max = tar[i];
            }
            sum += tar[i];
        }
        int[] temp = new int[max];
        for (int i = 0; i < tar.length; i++) {
            temp[tar[i]-1] = 1; // 存在赋值1
        }
        int height = sum >> 1;
        while (height > 0) {
            if (checkHeight(temp, height, height)) {
                return height;
            } else {
                height -= 1;
            }
        }
        return 0;
    }

    private static boolean checkHeight(int[] temp, int height ,int target) {
        if (height <= 0) {
            return true;
        }
        for (int i = Math.min(temp.length-1, height-1);i >=0; i--) {
            if (temp[i] != 1) {
                continue; // 跳过
            }
            if (temp[i] == 1 && i == height-1) {
                temp[i] = 2;
                if (target == 0) {
                    return true; // 找到
                } else {
                    // 再找一遍
                    if(checkHeight(temp, target, 0)) {
                        return true;
                    } else {
                        // 未找到归还
                        temp[i] = 1;
                    }
                }
            } else if (temp[i] == 1) {
                temp[i] = 2;
                if (checkHeight(temp, height-i-1, target)) {
                    // 找到返回
                    return true;
                } else {
                    // 未找到归还
                    temp[i] = 1;
                }
            }
        }
        return false;
    }
}
