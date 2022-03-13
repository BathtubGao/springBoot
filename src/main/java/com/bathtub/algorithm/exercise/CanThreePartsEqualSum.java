package com.bathtub.algorithm.exercise;

public class CanThreePartsEqualSum {

    public static boolean canThreePartsEqualSum(int[] A) {
        int max = 0;
        for(int i : A) {
            max = max + i;
        }
        if (max % 3 != 0) {
            return false;
        }
        int target = max / 3;
        int plus = 0;
        int index = 0;
        for (int i : A) {
            plus = plus + i;
            if(plus == target){
                plus = 0;
                index++;
            }
        }
        if (plus == 0 && (index == 3 || index > 3 && target == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] it = {10,-10,10,-10,10,-10,10,-10};
        System.out.println(canThreePartsEqualSum(it));
    }
}
