package org.springframework.core.io;

/**
 * Resource Loader, help us to locate the resource and return it by the locationPath we pass
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public interface ResourceLoader {

    String CLASSPATH_PREFIX = "";

    /**
     * locate the resource and return it by the locationPath we pass
     *
     * @param location location
     * @return located resource
     */
    Resource getResource(String location);
}
