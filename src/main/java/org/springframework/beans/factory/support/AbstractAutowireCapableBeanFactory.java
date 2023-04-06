package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.util.Assert;

/**
 * 具备Autowire能力的BeanFactory
 * <p>在此类负责生产Bean实例</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        Assert.notNull(instantiationStrategy, "instantiationStrategy cannot be null");
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = instantiationStrategy.instantiate(beanName, beanDefinition);
        // 注册到bean容器
        register(beanName, bean);
        return bean;
    }
}
