package com.bing.springcode.importbeandefinitionscan2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * 通过BingScan，importBeanDefinitionRegister扫描
 * BingScan配置的目录，加载@Bean的interface，并根据interface，生成默认实现
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringBootApplica.class);
        context.refresh();

        First first = context.getBean(First.class);
        first.getList("123","bing");

        Second second = context.getBean(Second.class);
        second.getResult("helloBean");


        System.in.read();
    }
}
