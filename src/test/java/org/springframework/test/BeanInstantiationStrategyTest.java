package org.springframework.test;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * Description
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */

public class BeanInstantiationStrategyTest {

    @Test
    public void testSimpleBeanInstantiationStrategy() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(StockVerifier.class);
        beanFactory.registerBeanDefinition("stockVerifier", beanDefinition);
        StockVerifier stockVerifier = (StockVerifier) beanFactory.getBean("stockVerifier");
        stockVerifier.verify("A0001_SKU001's Stock");
    }

    @Test
    public void testCglibBeanInstantiationStrategy() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 设置Cglib实例化策略（真正的Spring设计中，当beanDefinition需要进行method重新时，才需要用到CGLIB进行实例化。
        // 因为CGLIB生成代理对象，而代理对象就可以做到对原有的bean方法进行拦截重写的操作
        beanFactory.setInstantiationStrategy(new CglibSubclassingInstantiationStrategy());

        BeanDefinition beanDefinition = new BeanDefinition(StockVerifier.class);
        beanFactory.registerBeanDefinition("stockVerifier", beanDefinition);
        StockVerifier stockVerifier = (StockVerifier) beanFactory.getBean("stockVerifier");
        stockVerifier.verify("A0001_SKU001's Stock");
    }
}
