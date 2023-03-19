package org.springframework.beans.factory.config;

/**
 * 单例Bean注册表接口
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */
public interface SingletonBeanRegistry {
    /**
     * 根据bean名称，注册bean对象到bean容器中
     *
     * @param beanName     beanName
     * @param beanInstance beanInstance
     */
    void register(String beanName, Object beanInstance);
}
