package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

/**
 * 简单的Bean实例策略实现
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/6
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getClazz();
        try {
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
            return declaredConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new BeansException(MessageFormat.format("bean create failed, beanName:{0}", beanName), e);
        }
    }
}
