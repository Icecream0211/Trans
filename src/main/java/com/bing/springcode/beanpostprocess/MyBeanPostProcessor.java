package com.bing.springcode.beanpostprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * bean的后置处理器，面向所有的bean
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor, BeanDefinitionRegistryPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(MyBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            log.info("执行MyBeanPostProcessor的postProcessBeforeInitialization方法");
            ((Person) bean).setName("王兵");
            ((Person) bean).setId(1);
        }
        if (bean instanceof Address) {
            log.info("执行MyBeanPostProcessor的postProcessBeforeInitialization方法");
            ((Address) bean).setProvence("陕西");
            ((Address) bean).setCity("西安");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            log.info("执行MyBeanPostProcessor的postProcessAfterInitialization方法");
        }
        if (bean instanceof Address) {
            log.info("执行MyBeanPostProcessor的postProcessAfterInitialization方法");
            log.info("执行MyBeanPostProcessor的postProcessAfterInitialization方法,设置dis");
            ((Address) bean).setDistrict(System.getenv("person.dis"));
        }

        return bean;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.info("执行MyBeanPostProcessor的postProcessBeanDefinitionRegistry方法");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("执行MyBeanPostProcessor的postProcessBeanFactory方法");
    }
}

