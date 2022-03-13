package com.bathtub.algorithm.exercise;


import java.util.ArrayList;
import java.util.List;

/**
  * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *  P   A   H   N
 *  A P L S I I G
 *  Y   I   R
  * @author 17031612
  * @date 2021/9/18
  */
public class ZConvert {
    public static String convert(String str, int numRows) {
        if (null == str || str.length() < 1 || numRows <= 1 || str.length() < numRows) {
            return str;
        }
        char[] charArray = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        int rowNum = 0;
        int interval = 2 * (numRows - 1);
        int rowInterval;
        while (rowNum < numRows) {
            int index = rowNum;
            rowInterval = 2 * (numRows - rowNum - 1);
            while (charArray.length > index) {
                sb.append(charArray[index]);
                if (rowInterval == 0 || interval == rowInterval){
                    index = index + interval;
                } else {
                    index = index + rowInterval;
                    rowInterval = interval - rowInterval;
                }
            }
            rowNum++;
        }
        return sb.toString();
    }

    public String convertNew(String s, int numRows) {
        if(numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows -1) flag = - flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) res.append(row);
        return res.toString();
    }


    public static void main(String[] args) {
        String str = "ABC";
        String result = convert(str, 2);
        System.out.println(result);
    }
}
