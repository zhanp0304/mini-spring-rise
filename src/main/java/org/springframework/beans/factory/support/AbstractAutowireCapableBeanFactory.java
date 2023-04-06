package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.PropertyValues;
import org.springframework.util.Assert;

import java.lang.reflect.Field;

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
        // populate bean with property-values
        populateBean(bean, beanDefinition);
        // 注册到bean容器
        register(beanName, bean);
        return bean;
    }

    @SuppressWarnings("all")
    private void populateBean(Object bean, BeanDefinition beanDefinition) {
        Assert.notNull(beanDefinition, "beanDefinition cannot be null");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            for (PropertyValues.PropertyValue value : propertyValues.getValues()) {
                Field field = bean.getClass().getDeclaredField(value.getPropertyName());
                field.setAccessible(true);
                field.set(bean, value.getValue());
            }
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("propertyName not match with the original Bean", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("no access permission", e);
        }
    }
}
