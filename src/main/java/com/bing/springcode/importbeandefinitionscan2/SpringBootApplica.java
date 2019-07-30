package com.bing.springcode.importbeandefinitionscan2;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主入口配置类
 */
@SpringBootApplication(scanBasePackages = "com.bing.springcode.importbeandefinitionscan2")
@BingScan2(basePackages = "com.bing.springcode.importbeandefinitionscan2")
public class SpringBootApplica {
}
