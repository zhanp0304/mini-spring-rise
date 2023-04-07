package org.springframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.MessageFormat;

/**
 * File Resource
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public class FileSystemResource extends AbstractResource {

    private final File file;

    protected FileSystemResource(String location, File file) {
        super(location);
        this.file = file;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return Files.newInputStream(file.toPath());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(MessageFormat.format("location{0} is not valid", getLocation()), e);
        } catch (IOException e) {
            throw new IllegalStateException(MessageFormat.format("An exception occurred while open the file[{0}]", getLocation()), e);
        }
    }
}
