package com.bing.springcode.BeanInstantiation;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component("hello3")
public class Hello3 implements Hello {

    String name = "hello3";

    public Hello3() {
        log.warn("hello3 constructor");
    }
}
