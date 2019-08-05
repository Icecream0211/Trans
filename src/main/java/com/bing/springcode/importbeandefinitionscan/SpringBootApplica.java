package com.bing.springcode.importbeandefinitionscan;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 主入口配置类
 */
@SpringBootApplication(scanBasePackages = "com.bing.springcode.importbeandefinitionscan")
@BingScan(basePackages = "com.bing.springcode.importbeandefinitionscan")
public class SpringBootApplica {


}
