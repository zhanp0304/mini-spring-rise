package org.springframework.test;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * Description
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */

public class BeanDefinitionAndBeanRegistryTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(StockVerifier.class);
        beanFactory.registerBeanDefinition("stockVerifier", beanDefinition);
        StockVerifier stockVerifier = (StockVerifier) beanFactory.getBean("stockVerifier");
        stockVerifier.verify("A0001_SKU001's Stock");
    }
}
