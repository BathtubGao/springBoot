package com.bathtub.algorithm.exercise;


public class JZ12 {

    public static boolean hasPath(char[][] matrix, String word) {
        char[] tar = word.toCharArray();
        for (int n = 0; n < matrix.length; n++) {
            for (int m = 0; m < matrix[n].length; m++) {
                if (checkPath(matrix, n, m, tar, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkPath(char[][] matrix, int n, int m, char[] tar, int index) {
        if (n < 0 || m < 0 || n >= matrix.length || m >= matrix[n].length || index < 0) {
            return false;
        }
        if (matrix[n][m] != tar[index]) {
            return false;
        }
        if (index == tar.length - 1) {
            return true;
        }
        char temp = matrix[n][m];
        matrix[n][m] = '-';
        boolean flag = checkPath(matrix, n + 1, m, tar, index + 1) || checkPath(matrix, n, m + 1, tar, index + 1)
                || checkPath(matrix, n - 1, m, tar, index + 1) || checkPath(matrix, n, m - 1, tar, index + 1);
        matrix[n][m] = temp;
        return flag;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][] {{'A'}};
        System.out.println(hasPath(matrix, "B"));
    }
}
