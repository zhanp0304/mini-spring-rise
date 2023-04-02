package org.springframework.beans.support;

import org.springframework.beans.factory.BeanFactory;

/**
 * SingletonBeanRegistry
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public interface SingletonBeanRegistry extends BeanRegister, BeanFactory {
    /**
     * register the beanName into the singleton bean instance container
     *
     * @param beanName     beanName
     * @param beanInstance bean
     */
    void singletonBeanRegister(String beanName, Object beanInstance);
}
