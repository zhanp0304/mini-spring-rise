package org.springframework.beans.support;

import org.springframework.beans.definition.BeanDefinition;
import org.springframework.beans.definition.BeanDefinitionRegistry;
import org.springframework.beans.factory.AbstractBeanFactory;

import java.text.MessageFormat;

/**
 * AbstractAutowireCapableBeanFactory
 * <p>AbstractAutowireCapableBeanFactory has the ability about creating the bean when bean instance missing after using getBean() </p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    private final BeanDefinitionRegistry beanDefinitionRegistry;

    public AutowireCapableBeanFactory(SingletonBeanRegistry singletonBeanRegistry,
                                         BeanDefinitionRegistry beanDefinitionRegistry) {
        super(singletonBeanRegistry);
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionRegistry.getBeanDefinition(beanName);
    }

    /**
     * Create Bean instance And register it into the bean container
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     * @return bean instance
     */
    @Override
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class originalClazz = beanDefinition.getOriginalClazz();
        try {
            return originalClazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(MessageFormat.format("create Bean named with [{0}] failed", beanName));
        }
    }
}
