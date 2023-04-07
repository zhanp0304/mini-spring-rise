package org.springframework.test;

import org.junit.Test;
import org.springframework.bean.StockVerifyChainImpl;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.XmlBeanDefinitionReader;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * XmlBeanDefinitionTest
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public class XmlBeanDefinitionTest {
    @Test
    public void testXmlDefineBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition("classpath:spring.xml");

        StockVerifyChainImpl stockVerifyChainImpl = (StockVerifyChainImpl) beanFactory.getBean("stockVerifyChainImpl");

        // 验证XML定义Bean，是否正确将Bean加载到容器中
        assertThat(stockVerifyChainImpl).as("bean[stockVerifyChainImpl] created failed").isNotNull();

        // 验证Bean Ref成功注入
        assertThat(stockVerifyChainImpl.getStockVerifier()).as("bean ref not set correctly in stockVerifyChainImpl").isNotNull();

        // 验证Bean Property成功记录
        assertThat(stockVerifyChainImpl.getStockVerifier().getSkuCode()).isEqualTo("SKU001");
        assertThat(stockVerifyChainImpl.getStockVerifier().getWarehouseCode()).isEqualTo("A0001");

        stockVerifyChainImpl.verify("A0001_SKU001's Stock");
    }

    @Test
    public void testAssertJ(){
        try {
            // set a bad age to Mr Frodo which is really 33 years old.
            int age = 30;
            // specify a test description (call as() before the assertion !), it supports String format syntax.
            assertThat(age).as("check %s's age", "Rise").isEqualTo(25);
        } catch (AssertionError e) {
            assertThat(e).hasMessage("[check Rise's age] expected:<[25]> but was:<[30]>");
        }
    }
}
