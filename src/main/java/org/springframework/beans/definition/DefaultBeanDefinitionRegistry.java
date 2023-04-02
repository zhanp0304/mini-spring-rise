package org.springframework.beans.definition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DefaultBeanDefinitionRegister
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public class DefaultBeanDefinitionRegistry implements BeanDefinitionRegistry {

    private final Logger log = LoggerFactory.getLogger(DefaultBeanDefinitionRegistry.class);

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        if (beanDefinitionMap.containsKey(beanName)) {
            log.info("Attention! the beanName[{}] already exists, now its beanDefinition will be overriding", beanName);
        }
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }
}
