package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

import java.text.MessageFormat;

/**
 * 具备Autowire能力的BeanFactory
 * <p>在此类负责生产Bean实例</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getClazz();
        Object bean;
        try {
            bean = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException(MessageFormat.format("bean create failed, beanName:{0}", beanName), e);
        }

        // 注册到bean容器
        register(beanName, bean);
        return bean;
    }
}
