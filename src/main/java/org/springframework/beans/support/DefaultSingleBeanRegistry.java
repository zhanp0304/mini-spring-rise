package org.springframework.beans.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public class DefaultSingleBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    @Override
    public void registerSingletonBean(String beanName, Object beanInstance) {
        singletonObjects.put(beanName, beanInstance);
    }

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonObjects.get(beanName);
    }
}
