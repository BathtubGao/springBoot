package com.bathtub.algorithm.exercise;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/
 */
public class KWeakestRows {
    public static void main(String[] args) {
        int[] res = kWeakestRows(new int[][]{{1,1,1,1,1,1}, {1,1,1,1,1,1}, {1,1,1,1,1,1}}, 1);
        System.out.println(Arrays.toString(res));
    }

    public static int[] kWeakestRows(int[][] mat, int k) {
        int[] result = new int[k];
        int m = 0, n = 0, kIndex = 0;
        int[] has = new int[mat.length];
        while (kIndex < k) {
            if (has[m] == 1) {
                m++;
            } else {
                if (mat[m][n] == 0) {
                    result[kIndex++] = m;
                    has[m] = 1;
                }
                m++;
            }
            if (m >= mat.length) {
                m = 0;
                n++;
            }
            if (n >= mat[0].length) {
                int i = 0;
                while (kIndex < k && i < k) {
                    if (has[i] == 0) {
                        result[kIndex++] = i;
                    }
                    i++;
                }
            }
        }
        return result;
    }
}
