package com.bathtub.algorithm.exercise;

public class Test {

    public static void main(String[] args) {
        String str = "abbccd";
        System.out.println(comp1(str));
    }

    private static String comp(String str) {
        int[] dic = new int[26];
        char[] chars = str.toCharArray();
        for(char c : chars) {
            int index = c-'a';
            dic[index] = dic[index]+1;
        }
        StringBuilder sbs = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (dic[i] > 0) {
                char ci = (char)('a' + i);
                sbs.append(ci).append(dic[i]);
            }
        }

        return sbs.toString();
    }

    private static String comp1(String str) {
        if (str.length() == 0) {
            return str;
        }
        char[] chars = str.toCharArray();
        char temp = chars[0];
        int num = 1;
        StringBuilder sbs = new StringBuilder();
        int i = 1;
        while (i < chars.length) {
            if (chars[i] == temp) {
                num++;
                i++;
            } else {
                sbs.append(temp).append(num);
                temp = chars[i++];
                num = 1;
            }
        }
        sbs.append(temp).append(num);
        if (sbs.toString().length() < str.length()) {
            return sbs.toString();
        } else {
            return str;
        }

    }
}
