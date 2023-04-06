package org.springframework.beans.factory.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 对class中的field进行抽象，封装为PropertyValues
 * <p>除了能存储original class的成员属性以外，Spring还可以对其进行扩展</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/6
 */
public class PropertyValues {
    private final List<PropertyValue> values = new ArrayList<>(16);

    public static class PropertyValue {
        private String propertyName;
        private Object value;

        public PropertyValue() {
        }

        public PropertyValue(String propertyName, Object value) {
            this.propertyName = propertyName;
            this.value = value;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    public List<PropertyValue> getValues() {
        return values;
    }
}
