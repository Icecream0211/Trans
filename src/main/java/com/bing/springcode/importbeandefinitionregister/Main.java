package com.bing.springcode.importbeandefinitionregister;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ImportApplication.class);
        context.refresh();
        User person = context.getBean(User.class);
        System.out.println(person.getName());
        System.out.println(person.getResident().getProvence());
        System.in.read();
    }
}
