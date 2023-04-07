package org.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Spring用于抽象资源的统一顶层接口，以inputStream作为资源载体给到用户进行使用
 * <p>因为Spring作为集大成者，需要扫描classpath读取用户定义或者第三方包的类，所以classpath是一种资源</p>
 * <p>因为Spring需要支持强大的灵活扩展能力，需要解析与读取配置文件（如yaml,properties)，所以File是一种资源</p>
 * <p>因为URL是统一资源定位地址，必须要实现URL的读取和解析。所以URL也是一种资源，但这里注意：我们只实现网络URL，不实现file:xxx这种</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public interface Resource {
    /**
     * 返回输入流
     *
     * @return InputStream
     */
    InputStream getInputStream() throws FileNotFoundException;
}
