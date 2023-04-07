package org.springframework.core.io;

import org.springframework.util.Assert;

import java.io.InputStream;

/**
 * 抽象资源类
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public abstract class AbstractResource implements Resource {

    private final String location;

    protected AbstractResource(String location) {
        Assert.notNull(location, "location cannot be null");
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
