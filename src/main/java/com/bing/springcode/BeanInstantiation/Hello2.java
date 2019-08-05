package com.bing.springcode.BeanInstantiation;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component("hello2")
public class Hello2 implements Hello {

    String name = "hello2";

    public Hello2() {
        log.warn("hello2 constructor");
    }

}
