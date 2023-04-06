package org.springframework.test;

import org.junit.Test;
import org.rise.verifier.StockBatchVerifier;
import org.springframework.beans.definition.BeanDefinition;
import org.springframework.beans.definition.DefaultBeanDefinitionRegistry;
import org.springframework.beans.support.AutowireCapableBeanFactory;
import org.springframework.beans.support.DefaultSingleBeanRegistry;

/**
 * BeanFactoryTest
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public class BeanFactoryTest {

    @Test
    public void testGetBean() {
        String beanName = "stockVerifier";
        DefaultBeanDefinitionRegistry beanDefinitionRegistry = new DefaultBeanDefinitionRegistry();
        BeanDefinition definition = new BeanDefinition(StockBatchVerifier.class);
        beanDefinitionRegistry.registerBeanDefinition(beanName, definition);

        DefaultSingleBeanRegistry beanRegistry = new DefaultSingleBeanRegistry();

        AutowireCapableBeanFactory beanFactory = new AutowireCapableBeanFactory(beanRegistry, beanDefinitionRegistry);
        StockBatchVerifier verifier = (StockBatchVerifier) beanFactory.getBean(beanName);
        verifier.verify();
    }
}
