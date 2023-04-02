package org.springframework.beans.support;

/**
 * SingletonBeanRegistry
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public interface SingletonBeanRegistry {
    /**
     * register the beanName into the singleton bean instance container
     *
     * @param beanName     beanName
     * @param beanInstance bean
     */
    void registerSingletonBean(String beanName, Object beanInstance);

    /**
     * getSingletonBean by beanName
     *
     * @param beanName beanName
     * @return SingletonBean instance
     */
    Object getSingletonBean(String beanName);
}
