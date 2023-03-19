package org.springframework.beans.factory.support;

/**
 * Bean工厂，可向工厂获取Bean实例
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */
public interface BeanFactory {
    /**
     * 根据bean名称获取bean实例
     *
     * @param beanName beanName
     * @return bean实例
     */
    Object getBean(String beanName);
}
