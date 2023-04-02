package org.springframework.beans.factory;

import org.springframework.beans.definition.BeanDefinition;
import org.springframework.beans.support.SingletonBeanRegistry;

import java.text.MessageFormat;

/**
 * Abstract Bean Factory
 * <p>wrap the common procedure of getBean() into the abstract clazz template</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private final SingletonBeanRegistry singletonBeanRegistry;

    protected AbstractBeanFactory(SingletonBeanRegistry singletonBeanRegistry) {
        this.singletonBeanRegistry = singletonBeanRegistry;
    }

    @Override
    public Object getBean(String beanName) {
        Object beanInstance = singletonBeanRegistry.getSingletonBean(beanName);
        if (beanInstance != null) {
            return beanInstance;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        if (beanDefinition == null) {
            throw new IllegalArgumentException(MessageFormat.format("the beanDefinition named[{0}] not exists", beanName));
        }
        beanInstance = doCreateBean(beanName, beanDefinition);
        singletonBeanRegistry.registerSingletonBean(beanName, beanInstance);
        return beanInstance;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * create bean Instance which named with beanName
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     * @return bean Instance
     */
    protected abstract Object doCreateBean(String beanName, BeanDefinition beanDefinition);

}
