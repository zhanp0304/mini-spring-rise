package org.springframework.beans.support;

/**
 * Bean Instance Register
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public interface BeanRegister {

    /**
     * register the beanName into the bean instance container
     *
     * @param beanName     beanName
     * @param beanInstance bean
     */
    void registerBean(String beanName, Object beanInstance);
}
