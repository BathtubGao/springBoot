package com.bathtub.diyElement;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;


/**
 * 解析XSD文件中定义和组件定义
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    // Element对应的类
    protected Class getBeanClass(Element element) {
        return User.class;
    }

    // 从Element中解析并提取对应元素
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");
        // 将提取的数据放入到BeanDefinitionBuilder中，待完成所有bean的解析后统一注册到beanFactory中
        if (StringUtils.hasText(userName)) {
            bean.addPropertyValue("userName", userName);
        }
        if (StringUtils.hasText(email)) {
            bean.addPropertyValue("email", email);
        }
    }
}
