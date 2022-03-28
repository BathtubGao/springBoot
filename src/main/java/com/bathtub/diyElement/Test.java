package com.bathtub.diyElement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("beans-diy.xml");
        User user = (User) bf.getBean("test");
        System.out.println(user.toString());
    }
}
