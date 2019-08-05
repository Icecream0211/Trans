package com.bing.springcode.importbeandefinitionscan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class HelloApplicationRunListener implements SpringApplicationRunListener {
    public HelloApplicationRunListener(SpringApplication application, String[]  args){
        System.out.println("constructor");
    }

    @Override
    public void starting() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println(environment.toString());

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println(context.getBeanDefinitionNames().toString());

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println(context.getBeanDefinitionNames().toString());


    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println(context.getBeanDefinitionNames().toString());

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
