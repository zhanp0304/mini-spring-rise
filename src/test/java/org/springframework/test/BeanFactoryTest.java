package org.springframework.test;

import org.junit.Test;
import org.rise.verifier.StockBatchVerifier;
import org.springframework.beans.definition.BeanDefinition;
import org.springframework.beans.definition.DefaultBeanDefinitionRegister;
import org.springframework.beans.support.AutowiredSingletonBeanFactory;

/**
 * BeanFactoryTest
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public class BeanFactoryTest {

    @Test
    public void testGetBean() {
        String beanName = "stockVerifier";
        DefaultBeanDefinitionRegister beanDefinitionRegister = new DefaultBeanDefinitionRegister();
        BeanDefinition definition = new BeanDefinition(StockBatchVerifier.class);
        beanDefinitionRegister.registerBeanDefinition(beanName, definition);

        AutowiredSingletonBeanFactory beanFactory = new AutowiredSingletonBeanFactory(beanDefinitionRegister);
        StockBatchVerifier verifier = (StockBatchVerifier) beanFactory.getBean(beanName);
        verifier.verify();
    }
}
