package com.bing.springcode.importbeandefinitionregister;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 第一种方法使用ImportBeanDefinitionRegister，使用扫描器，
 * 扫描我们的注解，将注解放置到Spring中，让spring
 * 根据注解load我们的bean
 */
public class MyBeanImportFirst
        implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanFactoryAware {
    private  static final Logger log = LoggerFactory.getLogger(MyBeanImportFirst.class);

    private ResourceLoader resourceLoader;

    private BeanFactory beanFactory;

    /**
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        MyClassPathBeanDefinitionScanner scanner = new MyClassPathBeanDefinitionScanner(registry, false);
        scanner.setResourceLoader(resourceLoader);
        scanner.registerFilters();
        scanner.doScan("com.bing.importbeandefinitionregister");
    }

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
}
