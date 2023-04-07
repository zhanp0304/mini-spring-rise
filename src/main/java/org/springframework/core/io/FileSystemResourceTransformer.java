package org.springframework.core.io;

import java.io.File;

/**
 * FileSystemResourceTransformer
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public class FileSystemResourceTransformer implements ResourceTransformer {

    @Override
    public Resource transform(String location) {
        File file = new File(location);
        if (file.exists()) {
            return new FileSystemResource(location, file);
        }
        return null;
    }
}
