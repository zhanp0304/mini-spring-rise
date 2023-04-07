package org.springframework.test;

import org.junit.Test;
import org.springframework.core.Ordered;
import org.springframework.core.io.*;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.io.IoUtil;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Description
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public class ResourceAndResourceLoaderTest {

    @Test
    public void testResourceAndResourceLoader() throws FileNotFoundException {
        FileSystemResourceTransformer fileSystemResourceTransformer = new FileSystemResourceTransformer();
        UrlResourceTransformer urlResourceTransformer = new UrlResourceTransformer();
        ClassPathResourceTransformer classPathResourceTransformer = new ClassPathResourceTransformer();

        List<ResourceTransformer> resourceTransformers = new ArrayList<>(3);
        resourceTransformers.add(fileSystemResourceTransformer);
        resourceTransformers.add(urlResourceTransformer);
        resourceTransformers.add(classPathResourceTransformer);

        resourceTransformers = resourceTransformers.stream().sorted(Comparator.comparingInt(Ordered::getOrder)).collect(Collectors.toList());
        ResourceTransformerChain transformerChain = new ResourceTransformerChain(resourceTransformers);
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(transformerChain);

        Resource classpathResource = resourceLoader.getResource("classpath:rise.txt");
        String content = IoUtil.readUtf8(classpathResource.getInputStream());
        assertThat(content).isEqualTo("Hello, I'm Rise");

        Resource fileResource = resourceLoader.getResource("src/test/resources/rise.txt");
        content = IoUtil.readUtf8(fileResource.getInputStream());
        assertThat(content).isEqualTo("Hello, I'm Rise");

        Resource urlResource = resourceLoader.getResource("https://www.bilibili.com");
        content = IoUtil.readUtf8(urlResource.getInputStream());
        assertThat(content).isNotNull();
    }
}
