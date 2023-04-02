package org.springframework.beans.factory;

import org.springframework.beans.definition.BeanDefinition;

import java.text.MessageFormat;

/**
 * Abstract Bean Factory
 * <p>wrap the common procedure of getBean() into the abstract clazz template</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public abstract class AbstractBeanFactory implements BeanFactory {


    @Override
    public Object getBean(String beanName) {
        Object beanInstance = getBeanInstance(beanName);
        if (beanInstance != null) {
            return beanInstance;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        if (beanDefinition == null) {
            throw new IllegalArgumentException(MessageFormat.format("the beanDefinition named[{0}] not exists", beanName));
        }
        return doCreateBean(beanName);
    }

    public abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * require bean instance from the beanDefinitionMap
     *
     * @param beanName beanName
     * @return bean instance
     */
    public abstract Object getBeanInstance(String beanName);

    /**
     * create bean Instance which named with beanName
     *
     * @param beanName beanName
     * @return bean Instance
     */
    public abstract Object doCreateBean(String beanName);

}
