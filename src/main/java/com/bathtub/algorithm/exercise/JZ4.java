package com.bathtub.algorithm.exercise;

/**
  * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。'
  * [
  * [1,2,8,10],
  * [2,4,9,12],
  * [4,7,10,13],
  * [6,8,11,15]
  * ]
  * 给定 target = 7，返回 true。
  * 给定 target = 3，返回 false。
  * @author 17031612
  * @date 2021/12/23
  */
public class JZ4 {

    public static boolean find(int target, int [][] array) {
        if (null == array || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int index1 = 0;
        int index2 = array[0].length - 1;
        while (index1 < array.length && index2 >= 0) {
            if (array[index1][index2] == target) {
                return true;
            } else if(array[index1][index2] < target)  {
                index1++;
            } else if(array[index1][index2] > target) {
                index2--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][]arr = {{1,2,8,9},{2,4,9,12},{4,7,10,13}, {6,8,11,15}};
//        int[][]arr = {{}};
        System.out.println(find(20, arr));
    }
}
