package com.bing.springcode.importbeandefinitionregister;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

@MyAnnoation
public  class MyAnnotationBeanPostProcessor implements BeanPostProcessor, BeanDefinitionRegistryPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(MyAnnotationBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof User) {
            log.info("执行MyAnnotationBeanPostProcessor的postProcessBeforeInitialization方法");
            ((User) bean).setName("王兵");
        }
        if (bean instanceof Residence) {
            log.info("执行MyAnnotationBeanPostProcessor的postProcessBeforeInitialization方法");
            ((Residence) bean).setProvence("陕西");
            ((Residence) bean).setCity("西安");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof User) {
            log.info("执行MyAnnotationBeanPostProcessor的postProcessAfterInitialization方法");
        }
        if (bean instanceof Residence) {
            log.info("执行MyAnnotationBeanPostProcessor的postProcessAfterInitialization方法");
        }
        return bean;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.info("执行MyAnnotationBeanPostProcessor的postProcessBeanDefinitionRegistry方法");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("执行MyAnnotationBeanPostProcessor的postProcessBeanFactory方法");
    }
}