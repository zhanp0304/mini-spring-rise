package org.springframework.core.io;

import org.springframework.core.Ordered;
import org.springframework.util.ResourceUtil;

/**
 * ClassPathResourceTransformer
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public class ClassPathResourceTransformer implements ResourceTransformer {

    @Override
    public Resource transform(String location) {
        if (location.startsWith(ResourceUtil.CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(ResourceUtil.CLASSPATH_URL_PREFIX.length()));
        }
        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
