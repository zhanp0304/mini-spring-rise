package org.springframework.core.io;

import org.springframework.core.Ordered;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * UrlResourceTransformer
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public class UrlResourceTransformer implements ResourceTransformer {

    @Override
    public Resource transform(String location) throws MalformedURLException {
        URL url = new URL(location);
        return new UrlResource(location, url);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
