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

    private void populateBean(Object bean, BeanDefinition beanDefinition) {
        Assert.notNull(beanDefinition, "beanDefinition cannot be null");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            for (PropertyValues.PropertyValue pv : propertyValues.getValues()) {
                if (pv.getValue() instanceof BeanReference) {
                    processWithBeanReference(bean, pv.getPropertyName(), (BeanReference) pv.getValue());
                    continue;
                }

                applyPropertyValue(bean, pv.getPropertyName(), pv.getValue());
            }
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("propertyName not match with the original Bean", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("no access permission", e);
        }
    }

    private void processWithBeanReference(Object bean, String propertyName, BeanReference beanReference) throws NoSuchFieldException, IllegalAccessException {
        String beanName = beanReference.getBeanName();
        Object memberBean = getBean(beanName);
        applyPropertyValue(bean, propertyName, memberBean);
    }

    @SuppressWarnings("all")
    private void applyPropertyValue(Object bean, String propertyName, Object propertyValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = bean.getClass().getDeclaredField(propertyName);
        field.setAccessible(true);
        field.set(bean, propertyValue);
    }
}
