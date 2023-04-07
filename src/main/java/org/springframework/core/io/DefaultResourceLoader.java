package org.springframework.core.io;

import java.text.MessageFormat;

/**
 * Default Resource Loader
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public class DefaultResourceLoader implements ResourceLoader {

    private final ResourceTransformerChain resourceTransformerChain;

    public DefaultResourceLoader(ResourceTransformerChain resourceTransformerChain) {
        this.resourceTransformerChain = resourceTransformerChain;
    }

    @Override
    public Resource getResource(String location) {
        Resource resource = resourceTransformerChain.transform(location);
        if (resource == null) {
            throw new IllegalArgumentException(MessageFormat.format("the location[{0}] is invalid, please input the correct one again", location));
        }
        return resource;
    }
}
