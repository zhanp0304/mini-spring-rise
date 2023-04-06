package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * Bean实例化策略
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/6
 */
public interface InstantiationStrategy {
    /**
     * 根据BeanDefinition 初始化Bean
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     * @return Bean实例对象
     * @throws BeansException throw it when instantiation failed
     */
    Object instantiate(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
