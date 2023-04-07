package org.springframework.beans.factory.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.ResourceTransformerChain;

/**
 * AbstractBeanDefinitionReader
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
@Slf4j
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final ResourceLoader resourceLoader;

    private final BeanDefinitionRegistry beanDefinitionRegistry;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = new DefaultResourceLoader(ResourceTransformerChain.initBuild());
    }

    @Override
    public void loadBeanDefinitions(String... locations) {
        for (String location : locations) {
            Resource resource = resourceLoader.getResource(location);
            if (resource == null) {
                log.warn("resource in the location[{}] is null! So we skip its beanDefinition loading", location);
                continue;
            }
            loadBeanDefinition(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource[] resources) {
        for (Resource resource : resources) {
            if (resource == null) {
                log.warn("resource is null! So we skip its beanDefinition loading");
                continue;
            }
            loadBeanDefinition(resource);
        }
    }

    @Override
    public void loadBeanDefinition(String location) {
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinition(resource);
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return beanDefinitionRegistry;
    }
}
