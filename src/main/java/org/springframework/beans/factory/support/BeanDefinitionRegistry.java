package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition 注册表接口
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册BeanDefinition
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
