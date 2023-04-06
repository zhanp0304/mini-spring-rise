package org.springframework.beans.factory.config;

import java.util.List;

/**
 * bean定义（包含一些class信息，如class类型，构造参数，属性值等）
 * <p>这里简化一下，只包含类信息</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */
public class BeanDefinition {

    private final Class<?> clazz;

    private final PropertyValues propertyValues;

    public BeanDefinition(Class<?> clazz) {
        this.clazz = clazz;
        this.propertyValues = new PropertyValues();
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void addPropertyValue(PropertyValues.PropertyValue propertyValue) {
        List<PropertyValues.PropertyValue> values = this.propertyValues.getValues();
        values.add(propertyValue);
    }
}
