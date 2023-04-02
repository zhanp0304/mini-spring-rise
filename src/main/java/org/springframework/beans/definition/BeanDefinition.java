package org.springframework.beans.definition;

/**
 * Bean Definition Class, a unified class for the User Definition Business Clazz
 * <p>Bean Definition holds the clazz abstract info of the original clazz user defined</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/2
 */
public class BeanDefinition {

    private final Class<?> originalClazz;

    public BeanDefinition(Class<?> originalClazz) {
        this.originalClazz = originalClazz;
    }

    public Class<?> getOriginalClazz() {
        return originalClazz;
    }
}
