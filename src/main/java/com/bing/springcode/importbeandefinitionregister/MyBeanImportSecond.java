package com.bing.springcode.importbeandefinitionregister;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 第二种用法
 * 自己将类转换为bedefinition，注册到spring中
 */
public class MyBeanImportSecond implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanFactoryAware {
    private  static final Logger log = LoggerFactory.getLogger(MyBeanImportSecond.class);


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

        /**
         * 注册方法1
         *
         */
        /*BeanDefinition userDefinition = BeanDefinitionBuilder.rootBeanDefinition(User.class)
                .addConstructorArgReference("residence").getBeanDefinition();
        registry.registerBeanDefinition("user",userDefinition);

        BeanDefinition residenceDefinition = BeanDefinitionBuilder.rootBeanDefinition(Residence.class).getBeanDefinition();
        registry.registerBeanDefinition("residence",residenceDefinition);*/

        /**
         * 注册方法2
         * 方法1和方法2本质一样，只是beandefinition的定义是否使用了BeanDefinitionBuilder;
         * addConstructorArgReference方法，是指如果user类的构造方法中有依赖注入，则使用。还有其他方法，可以自己查看
         */
        RootBeanDefinition beanDefinition = new RootBeanDefinition(User.class);
        registry.registerBeanDefinition("user",beanDefinition);
        RootBeanDefinition beanDefinitionRe = new RootBeanDefinition(Residence.class);
        registry.registerBeanDefinition("residence",beanDefinitionRe);
    }
}
