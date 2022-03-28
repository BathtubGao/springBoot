package com.bathtub.diyElement;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 将组件注册到Spring容器中
 */
public class MyNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        // 当遇到<user:开头的自定义标签，就会把这个元素丢给UserBeanDefinitionParser去解析
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
