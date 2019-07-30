package com.bing.springcode.beanpostprocess;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DemoApplication.class);
        context.refresh();
        Person person = context.getBean(Person.class);
        System.out.println(person.getName());
        System.out.println(person.getAddress().provence);
        System.out.println(person.getAddress().getDistrict());
        System.in.read();
    }
}
