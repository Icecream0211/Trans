package com.bing.springcode.BeanInstantiation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com.bing.springcode.BeanInstantiation")
public class Application {

    @Autowired
    HelloTest helloTest;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        helloTest.use();
    }

}


