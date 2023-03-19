package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * 抽象的Bean工厂，聚合多个接口能力
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
        Object bean = singletonObjects.get(beanName);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    /**
     * 根据beanName获取beanDefinition
     *
     * @param beanName beanName
     * @return beanDefinition
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * 根据beanName, 以及beanDefinition创建bean实例
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     * @return bean实例
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}
