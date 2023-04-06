package org.springframework.test;

import org.junit.Test;
import org.springframework.bean.StockVerifier;
import org.springframework.bean.StockVerifyChainImpl;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.PropertyValues;
import org.springframework.beans.factory.support.BeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Description
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */

public class PopulateBeanWithBeanTest {

    @Test
    public void testPopulateBeanWithBeanValues() {
        // 注册stockVerifier beanDefinition
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(StockVerifier.class);
        beanFactory.registerBeanDefinition("stockVerifier", beanDefinition);

        String warehouseCode = "A0001";
        beanDefinition.addPropertyValue(new PropertyValues.PropertyValue(StockVerifier.FIELD_WAREHOUSE_CODE, warehouseCode));
        String skuCode = "SKU001";
        beanDefinition.addPropertyValue(new PropertyValues.PropertyValue(StockVerifier.FIELD_SKU_CODE, skuCode));

        // 注册StockVerifierChain beanDefinition
        BeanDefinition stockVerifyChainBd = new BeanDefinition(StockVerifyChainImpl.class);
        beanFactory.registerBeanDefinition("stockVerifyChainImpl", stockVerifyChainBd);
        stockVerifyChainBd.addPropertyValue(new PropertyValues.PropertyValue("stockVerifier", (BeanReference) () -> "stockVerifier"));

        // 只初始化stockVerifyChainImpl, 由内部递归创建StockVerifier

        String anything = "A0001_SKU001's Stock";
        StockVerifyChainImpl stockVerifyChainImpl = (StockVerifyChainImpl) beanFactory.getBean("stockVerifyChainImpl");
        assertThat(stockVerifyChainImpl.getStockVerifier()).isNotNull();
        stockVerifyChainImpl.verify(anything);

//        StockVerifier stockVerifier = (StockVerifier) beanFactory.getBean("stockVerifier");
//        stockVerifier.verify(anything);
//        assertThat(stockVerifier.getSkuCode()).isEqualTo(skuCode);
//        assertThat(stockVerifier.getWarehouseCode()).isEqualTo(warehouseCode);
    }

    /**
     * 先创建成员，再创建父类
     */
    @Test
    public void testPopulateBeanWithBeanValues_2() {
        // 注册stockVerifier beanDefinition
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(StockVerifier.class);
        beanFactory.registerBeanDefinition("stockVerifier", beanDefinition);

        String warehouseCode = "A0001";
        beanDefinition.addPropertyValue(new PropertyValues.PropertyValue(StockVerifier.FIELD_WAREHOUSE_CODE, warehouseCode));
        String skuCode = "SKU001";
        beanDefinition.addPropertyValue(new PropertyValues.PropertyValue(StockVerifier.FIELD_SKU_CODE, skuCode));

        // 注册StockVerifierChain beanDefinition
        BeanDefinition stockVerifyChainBd = new BeanDefinition(StockVerifyChainImpl.class);
        beanFactory.registerBeanDefinition("stockVerifyChainImpl", stockVerifyChainBd);
        stockVerifyChainBd.addPropertyValue(new PropertyValues.PropertyValue("stockVerifier", (BeanReference) () -> "stockVerifier"));

        String anything = "A0001_SKU001's Stock";
        StockVerifier stockVerifier = (StockVerifier) beanFactory.getBean("stockVerifier");
        stockVerifier.verify(anything);
        assertThat(stockVerifier.getSkuCode()).isEqualTo(skuCode);
        assertThat(stockVerifier.getWarehouseCode()).isEqualTo(warehouseCode);

        StockVerifyChainImpl stockVerifyChainImpl = (StockVerifyChainImpl) beanFactory.getBean("stockVerifyChainImpl");
        assertThat(stockVerifyChainImpl.getStockVerifier()).isNotNull();
        stockVerifyChainImpl.verify(anything);
    }
}
