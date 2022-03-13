package com.bathtub.algorithm.exercise;

import java.util.LinkedList;

public class ValidStr {

    public static void main(String[] args) {
        System.out.println(check("{[]}"));
    }

    private static boolean check(String str) {
        char[] chars = str.toCharArray();
        LinkedList<Character> list = new LinkedList<>();
        for (Character c : chars) {
            if (checkBeg(c)) {
                list.add(c);
            } else {
                if (list.isEmpty()) {
                    return false;
                } else {
                    Character last = list.getLast();
                    if (!eq(last, c)) {
                        return false;
                    } else {
                        list.removeLast();
                    }
                }
            }
        }
        if (list.isEmpty()) {
            return true;
        }
        return false;
    }

    private static boolean eq(Character c1, Character c2) {
        if ('(' == c1 && ')' == c2) {
            return true;
        }
        if ('[' == c1 && ']' == c2)
            return true;
        if ('{' == c1 && '}' == c2)
            return true;
        return false;
    }

    private static boolean checkBeg(Character c) {
        if ('(' == c || '[' == c || '{' == c) {
            return true;
        }
        return false;
    }
}
