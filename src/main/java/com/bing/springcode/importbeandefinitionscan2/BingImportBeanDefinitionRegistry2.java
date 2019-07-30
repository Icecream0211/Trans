package com.bing.springcode.importbeandefinitionscan2;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.util.Map;

/**
 * importBeanDefinitionRegistry和
 * BeanDefinitionRegistryPostProcessor 效果一样，只是阶段不同，参考
 * https://blog.csdn.net/lichuangcsdn/article/details/89930945
 *
 */

public class BingImportBeanDefinitionRegistry2 implements
        ImportBeanDefinitionRegistrar,ResourceLoaderAware {


    private ResourceLoader resourceLoader;
    private ResourcePatternResolver resourcePatternResolver;

    private ApplicationContext applicationContext;

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private MetadataReaderFactory metadataReaderFactory;
    private static final String BASE_SCAN_PACKAGE_ATTR = "basePackages";


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry){

        AnnotationAttributes mapperScanAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(BingScan2.class.getName()));
        String basePackage = "";
        Map<String, Object> annotationAttrs =  importingClassMetadata.getAnnotationAttributes(BingScan2.class.getName());
        if (annotationAttrs.containsKey(BASE_SCAN_PACKAGE_ATTR)){
            basePackage = (String) annotationAttrs.get(BASE_SCAN_PACKAGE_ATTR);
        }
        BingClassPathBeanDefinitionScanner scanner = new BingClassPathBeanDefinitionScanner(registry, false);
        scanner.setResourceLoader(resourceLoader);
        scanner.registerFilters();
        scanner.doScan(basePackage);
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

}
