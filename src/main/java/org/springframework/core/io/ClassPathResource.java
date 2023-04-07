package org.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * classpath资源
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public class ClassPathResource extends AbstractResource {

    protected ClassPathResource(String location) {
        super(location);
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(getLocation());
        if (inputStream == null) {
            throw new FileNotFoundException(getLocation() + " cannot be opened because it does not exist");
        }
        return inputStream;
    }
}
