package com.bathtub.springBeanTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    /**
      * 循环依赖
      * @author 17031612
      * @date 2021/9/16
      */
    public static class CircleTest {
        private final static Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

        private static <T> T getBean(Class<T> beanClass) throws IllegalAccessException, InstantiationException {
           String beanName = beanClass.getSimpleName().toLowerCase();
           if (singletonObjects.containsKey(beanName)) {
               return (T) singletonObjects.get(beanName);
           }

           // 实例化对象入缓存
           Object obj = beanClass.newInstance();
           singletonObjects.put(beanName, obj);
           // 属性填充补全对象
           Field[] fields = obj.getClass().getDeclaredFields();
           for (Field field : fields) {
               field.setAccessible(true);
               Class<?> fieldClass = field.getType();
               String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
               field.set(obj, singletonObjects.containsKey(fieldBeanName) ? singletonObjects.get(fieldBeanName) : getBean(fieldClass));
               field.setAccessible(false);
           }
           return (T) obj;
        }

        public static void main(String[] args) throws InstantiationException, IllegalAccessException {
            System.out.println(getBean(B.class).getA());
            System.out.println(getBean(A.class).getB());
        }

        class A {
            private B b;

            public B getB() {
                return b;
            }

            public void setB(B b) {
                this.b = b;
            }
        }

        class B {
            private A a;

            public A getA() {
                return a;
            }

            public void setA(A a) {
                this.a = a;
            }
        }
    }
}
