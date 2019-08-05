package com.bing.springcode.BeanInstantiation;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component("hello1")
public class Hello1 implements Hello {
    private

    String name = "hello1";

    public Hello1() {
        log.warn("hello1 constructor");
    }
}

