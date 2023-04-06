package org.springframework.test;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.PropertyValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Description
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */

public class PopulateBeanTest {

    @Test
    public void testPopulateBeanWithPropertyValues() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(StockVerifier.class);
        beanFactory.registerBeanDefinition("stockVerifier", beanDefinition);

        String warehouseCode = "A0001";
        beanDefinition.addPropertyValue(new PropertyValues.PropertyValue(StockVerifier.FIELD_WAREHOUSE_CODE, warehouseCode));
        String skuCode = "SKU001";
        beanDefinition.addPropertyValue(new PropertyValues.PropertyValue(StockVerifier.FIELD_SKU_CODE, skuCode));

        StockVerifier stockVerifier = (StockVerifier) beanFactory.getBean("stockVerifier");
        stockVerifier.verify("A0001_SKU001's Stock");
        assertThat(stockVerifier.getSkuCode()).isEqualTo(skuCode);
        assertThat(stockVerifier.getWarehouseCode()).isEqualTo(warehouseCode);
    }
}
