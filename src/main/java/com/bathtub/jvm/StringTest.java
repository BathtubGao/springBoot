package com.bathtub.jvm;

/**
 * https://blog.csdn.net/tyyking/article/details/82496901
 * @author 17031612
 * @date 2022/2/25
 */
public class StringTest {
    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2); // false

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4); // true

        String s5 = new String("abc");
        String s6 = "abc";
        String s7 = new String("abc");
        System.out.println(s5 == s6.intern());
        System.out.println(s5 == s7.intern());
        System.out.println(s6 == s7.intern());
    }
}
