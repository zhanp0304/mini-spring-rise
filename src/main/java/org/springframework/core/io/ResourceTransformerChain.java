package org.springframework.core.io;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Resource Transformer Chain
 * <p>to transform the location to the correct and concrete resource by all the ResourceTransformers</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
@Slf4j
public class ResourceTransformerChain implements ResourceTransformer {
    private final List<ResourceTransformer> transformers;

    public ResourceTransformerChain(List<ResourceTransformer> transformers) {

        Assert.notNull(transformers, "transformers cannot be null");
        this.transformers = transformers;
    }

    /**
     * transform the location to the correct and concrete resource
     *
     * @param location location
     * @return Resource (null will be returned when not math any transformer)
     */
    @Override
    public Resource transform(String location) {
        Exception finalEx = null;
        for (ResourceTransformer transformer : transformers) {
            Resource resource = null;
            try {
                resource = transformer.transform(location);
                if (resource != null) {
                    return resource;
                }
            } catch (Exception e) {
                log.info("Transformer[{}] not match with the location[{}]", transformer.getClass().getName(), location);
                finalEx = e;
            }
        }
        log.error("not exists any available transformer match this location[{}]", location, finalEx);
        return null;
    }
}
