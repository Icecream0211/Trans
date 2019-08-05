package com.bing.springcode.BeanInstantiation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    /**
     * 在Bean实例化前执行
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("hello1".equals(beanName)) {
            log.error("hello1 - 实例化前");
        }
        if ("hello2".equals(beanName)) {
            log.error("hello2 - 实例化前");
        }
        if ("hello3".equals(beanName)) {
            log.error("hello3 - 实例化前");
        }
        if ("helloTest".equals(beanName)) {
            log.error("helloTest - 实例化前");
        }
        return null;
    }

    /**
     * 在Bean实例化后执行
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("hello1".equals(beanName)) {
            log.error("hello1 - 实例化后");
        }
        if ("hello2".equals(beanName)) {
            log.error("hello2 - 实例化后");
        }
        if ("hello3".equals(beanName)) {
            log.error("hello3 - 实例化后");
        }
        if ("helloTest".equals(beanName)) {
            log.error("helloTest - 实例化后 - {}", bean);
        }
        return true;
    }

    /**
     * 在Bean生命周期中的实例化后，填充属性阶段前执行
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if ("hello1".equals(beanName)) {
            log.error("hello1 - 填充属性前");
        }
        if ("hello2".equals(beanName)) {
            log.error("hello2 - 填充属性前");
        }
        if ("hello3".equals(beanName)) {
            log.error("hello3 - 填充属性前");
        }
        if ("helloTest".equals(beanName)) {
            log.error("helloTest - 填充属性前 - {}", bean);
        }
        return pvs;
    }



}
