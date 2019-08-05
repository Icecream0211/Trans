package com.bing.springcode.importbeandefinitionscan;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

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
