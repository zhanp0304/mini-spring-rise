package org.springframework.core.io;

import org.springframework.core.Ordered;

import java.net.MalformedURLException;

/**
 * transform the location to the correct and concrete resource
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public interface ResourceTransformer extends Ordered {
    /**
     * transform the location to the correct and concrete resource
     *
     * @param location location
     * @return Resource
     */
    Resource transform(String location) throws MalformedURLException;
}
