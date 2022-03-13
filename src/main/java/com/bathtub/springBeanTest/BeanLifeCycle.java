package com.bathtub.springBeanTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycle {

    public static void main(String[] args) {
        System.out.println("现在开始初始化容器");
        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("容器初始化成功");
        Person person = factory.getBean("person",Person.class);
        System.out.println(person);
        System.out.println("现在开始关闭容器！");
        factory.registerShutdownHook();
    }
}
