package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

/**
 * Description
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public class UrlResource extends AbstractResource {

    private final URL url;

    protected UrlResource(String location, URL url) {
        super(location);
        this.url = url;
    }

    @Override
    public InputStream getInputStream() {
        try {
            URLConnection urlConnection = url.openConnection();
            return urlConnection.getInputStream();
        } catch (IOException e) {
            throw new IllegalStateException(MessageFormat.format("An exception occurred while request the URL[{0}]", getLocation()), e);
        }
    }
}
