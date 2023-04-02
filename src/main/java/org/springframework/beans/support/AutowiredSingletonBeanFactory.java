package org.springframework.beans.support;

import org.springframework.beans.definition.BeanDefinition;
import org.springframework.beans.definition.BeanDefinitionRegister;

import java.text.MessageFormat;

/**
 * AutowiredSingletonBeanFactory
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public class AutowiredSingletonBeanFactory extends SingletonBeanRegister {

    public AutowiredSingletonBeanFactory(BeanDefinitionRegister beanDefinitionRegister) {
        super(beanDefinitionRegister);
    }

    @Override
    public Object doCreateBean(String beanName) {
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Class originalClazz = beanDefinition.getOriginalClazz();
        try {
            Object bean = originalClazz.newInstance();
            singletonBeanRegister(beanName, bean);
            return bean;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(MessageFormat.format("create Bean named with [{0}] failed", beanName));
        }
    }
}
