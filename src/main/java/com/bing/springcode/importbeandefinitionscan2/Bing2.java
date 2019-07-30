package com.bing.springcode.importbeandefinitionscan2;

import org.springframework.beans.factory.annotation.Lookup;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Bing2 {

}
