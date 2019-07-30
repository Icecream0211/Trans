package com.bing.springcode.importbeandefinitionscan2;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({BingImportBeanDefinitionRegistry2.class})
public @interface BingScan2 {
    String[] value() default {};
    String basePackages() default "";
    String lazyInitialization() default "";
}