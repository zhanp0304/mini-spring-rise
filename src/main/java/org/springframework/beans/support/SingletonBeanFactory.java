package org.springframework.beans.support;

import org.springframework.beans.definition.BeanDefinition;
import org.springframework.beans.definition.BeanDefinitionRegistry;
import org.springframework.beans.factory.AbstractBeanFactory;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * singleton bean register
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public abstract class SingletonBeanFactory extends AbstractBeanFactory implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    private final BeanDefinitionRegistry beanDefinitionRegistry;

    protected SingletonBeanFactory(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
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
        return beanDefinitionRegistry.getBeanDefinition(beanName);
    }

    @Override
    protected Object getBeanInstance(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * Create Bean instance And register it into the bean container
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     * @return bean instance
     */
    @Override
    protected abstract Object doCreateBean(String beanName, BeanDefinition beanDefinition);

    @Override
    public void singletonBeanRegister(String beanName, Object beanInstance) {
        singletonObjects.put(beanName, beanInstance);
    }
}
