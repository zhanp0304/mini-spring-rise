package org.springframework.beans.factory.support;

import org.springframework.core.io.Resource;

/**
 * BeanDefinitionReader
 * <p>Resource -> BeanDefinitionReader -> BeanDefinition</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public interface BeanDefinitionReader {

    /**
     * return the registry(almost the beanFactory) to register the beanDefinition
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * create beanDefinition and register BeanDefinition
     *
     * @param locations bean.xml location
     */
    void loadBeanDefinitions(String... locations);

    /**
     * create beanDefinition and register BeanDefinition
     *
     * @param location bean.xml location
     */
    void loadBeanDefinition(String location);

    /**
     * create beanDefinition and register BeanDefinition
     *
     * @param resource resource
     */
    void loadBeanDefinition(Resource resource);

    /**
     * create beanDefinition and register BeanDefinition
     *
     * @param resources resources
     */
    void loadBeanDefinitions(Resource[] resources);
}
