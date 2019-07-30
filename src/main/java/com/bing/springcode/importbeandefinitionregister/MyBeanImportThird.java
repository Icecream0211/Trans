package com.bing.springcode.importbeandefinitionregister;/*
package com.example.demo.importbeandefinitionregister;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;
import java.util.Map;

public class MyBeanImportThird implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanFactoryAware {
    private  static final Logger log = LoggerFactory.getLogger(MyBeanImportSecond.class);

    private static final String SCAN_PACKAGES = "scanPackages";
    private static final String ANNOTATION_CLASS = "annotationClass";

    private BeanFactory beanFactory;
    private ResourceLoader resourceLoader;


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        log.debug("注入的beanFactory"+this.beanFactory.toString());

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        log.debug("注入的resourceLoader"+this.beanFactory.toString());
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyAnnoationAutoScan.class.getName());
        String[] scanPackages = (String[])annotationAttributes.get(SCAN_PACKAGES);

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MyAnnoationAutoScan.class);
        beanDefinitionBuilder.addPropertyValue(SCAN_PACKAGES , scanPackages);

        registry.registerBeanDefinition(MyAnnoation, beanDefinitionBuilder.getBeanDefinition());

    }
}
*/
