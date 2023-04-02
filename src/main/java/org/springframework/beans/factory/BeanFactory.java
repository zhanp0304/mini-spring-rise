package org.springframework.beans.factory;

/**
 * Bean Factory Interface
 * <p>You can get the specified Bean what you want by using getBean(...)</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public interface BeanFactory {
    /**
     * get Bean instance by beanName
     *
     * @param beanName beanName
     * @return qualified bean instance
     */
    Object getBean(String beanName);
}
