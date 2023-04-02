package org.springframework.beans.support;

import org.springframework.beans.definition.BeanDefinition;
import org.springframework.beans.definition.BeanDefinitionRegister;
import org.springframework.beans.factory.AbstractBeanFactory;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * singleton bean register
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public class SingletonBeanRegister extends AbstractBeanFactory implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    private final BeanDefinitionRegister beanDefinitionRegister;

    public SingletonBeanRegister(BeanDefinitionRegister beanDefinitionRegister) {
        this.beanDefinitionRegister = beanDefinitionRegister;
    }

    @Override
    public void beanRegister(String beanName, Object beanInstance) {
        if (singletonObjects.containsKey(beanName)) {
            throw new IllegalArgumentException(MessageFormat.format("the bean named with [{0}] has already exists", beanName));
        }

        singletonObjects.put(beanName, beanInstance);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionRegister.getBeanDefinition(beanName);
    }

    @Override
    public Object getBeanInstance(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public Object doCreateBean(String beanName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void singletonBeanRegister(String beanName, Object beanInstance) {
        singletonObjects.put(beanName, beanInstance);
    }
}
