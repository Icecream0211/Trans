package com.bing.springcode.BeanInstantiation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 测试类
 */
@Slf4j
@Component
public class HelloTest {

    /**
     * 属性注入
     */
    @Autowired
    @Qualifier("hello1")
    Hello hello;


    /**
     * 构造注入
     * @param hello
     */
    @Autowired
    public HelloTest(@Qualifier("hello2") Hello hello) {
        this.hello = hello;
        log.error("test-constructor: " + hello.toString());
    }

    /**
     * 设值注入
     * @param hello
     */
    @Autowired
    public void setHello(@Qualifier("hello3") Hello hello) {
        this.hello = hello;

    }

    /**
     * Bean自定义初始化阶段
     */
    @PostConstruct
    public void init() {
        log.error("test-init: " + hello.toString());
    }

    /**
     * Bean准备好后，被调用
     */
    public void use() {
        log.error("use:" + hello.toString());
    }

    @Override
    public String toString() {
        return "hello=" + hello;
    }

}
