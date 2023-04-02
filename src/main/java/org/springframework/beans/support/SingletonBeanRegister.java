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
public abstract class SingletonBeanRegister extends AbstractBeanFactory implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    private final BeanDefinitionRegister beanDefinitionRegister;

    protected SingletonBeanRegister(BeanDefinitionRegister beanDefinitionRegister) {
        this.beanDefinitionRegister = beanDefinitionRegister;
    }

    @Override
    public void registerBean(String beanName, Object beanInstance) {
        if (singletonObjects.containsKey(beanName)) {
            throw new IllegalArgumentException(MessageFormat.format("the bean named with [{0}] has already exists", beanName));
        }

        singletonObjects.put(beanName, beanInstance);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionRegister.getBeanDefinition(beanName);
    }

    @Override
    protected Object getBeanInstance(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    protected abstract Object doCreateBean(String beanName);

    @Override
    public void singletonBeanRegister(String beanName, Object beanInstance) {
        singletonObjects.put(beanName, beanInstance);
    }
}
