package com.bing.springcode.importbeandefinitionscan;

import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({BingImportBeanDefinitionRegistry.class})
public @interface BingScan {
    String[] value() default {};
    String basePackages() default "";
    String lazyInitialization() default "";
}