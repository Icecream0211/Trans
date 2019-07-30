package com.bing.springcode.beanpostprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Iterator;

/**
 *
 * BeanFactoryPostProcessor允许使用者修改容器中的bean definitions
 * BeanFactoryPostProcessor可以与bean definitions打交道，但是千万不要进行bean实例化
 * （感觉这里应该说的是不要在BeanFactoryPostProcessor进行可能触发bean实例化的操作）。
 * 这么做可能会导致bean被提前实例化，会破坏容器造成预估不到的副作用。如果你需要hack到bean实例化过程，
 * 请考虑使用BeanPostProcessor。
 *
 *BeanFactoryPostProcessor是beanFactory的后置处理器接口，通过BeanFactoryPostProcessor，
 * 我们可以自定义spring容器中的bean定义，BeanFactoryPostProcessor是在spring容器加载了bean的定义信息之后、
 * bean实例化之前执行；
 *
 * 　　2、BeanFactoryPostProcessor类型的bean会被spring自动检测，在常规bean实例化之前被spring调用；
 *
 * 　　3、BeanFactoryPostProcessor的常用场景包括spring中占位符的处理、我们自定义的敏感信息的解密处理，当然不局限与此；
 *
 * 　　其实只要我们明白了BeanFactoryPostProcessor的生效时机，哪些场景适用BeanFactoryPostProcessor也就很清楚了

 *
 */
public class MyBeanPostFactoryProcesser implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.getProperties().setProperty("person.dis", "上海");
        System.out.println("调用了自定义的BeanFactoryPostProcessor " + beanFactory);
        Iterator it = beanFactory.getBeanNamesIterator();

        String[] names = beanFactory.getBeanDefinitionNames();
        // 获取了所有的bean名称列表
        for (int i = 0; i < names.length; i++) {
            String name = names[i];

            BeanDefinition bd = beanFactory.getBeanDefinition(name);
            System.out.println(name + " bean properties: " + bd.getPropertyValues().toString());
            // 本内容只是个demo，打印持有的bean的属性情况
        }
    }
}
