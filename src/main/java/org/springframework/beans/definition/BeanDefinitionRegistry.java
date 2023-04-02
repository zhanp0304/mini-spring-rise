package org.springframework.beans.definition;

/**
 * The Interface for registering the beanDefinition into the DefinitionRegister(almost the BeanDefinitionMap)
 * <p>It involves the register method and require method about the beanDefinition</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public interface BeanDefinitionRegistry {
    /**
     * register beanDefinition into the DefinitionRegister (almost the BeanDefinitionMap)
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * require beanDefinition from the container of the DefinitionRegister
     *
     * @param beanName beanName
     * @return beanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName);
}
