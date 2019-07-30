package com.bing.springcode.importbeandefinitionregister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 使用第几种，将第几种的import打开.
 * 但是，使用第二种，第三种，需要将MyAnnotationBeanPostProcesser的注解改为@component，让spring加载
 * 或者在config中，将MyAnnotationBeanPostProcesser注册上
 */
@Configuration
//@Import(MyBeanImportFirst.class)
@Import(MyBeanImportSecond.class)
public class Config {

    @Bean
    public  MyAnnotationBeanPostProcessor makeMyAnnotationBeanProcess(){
        return new MyAnnotationBeanPostProcessor();
    }

}
