package com.bing.springcode.importbeandefinitionscan2;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.io.IOException;
import java.util.Set;

public class BingClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public BingClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }


    protected void registerFilters() {
        addIncludeFilter(new AnnotationTypeFilter(Bing2.class));
        //addIncludeFilter(new AnnotationTypeFilter(MyAnnoationAutoScan.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }
}
