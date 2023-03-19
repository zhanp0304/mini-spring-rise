package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的单例Bean注册表接口实现
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    @Override
    public void register(String beanName, Object beanInstance) {
        singletonObjects.put(beanName, beanInstance);
    }
}
