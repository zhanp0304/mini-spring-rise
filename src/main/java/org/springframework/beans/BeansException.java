package org.springframework.beans;

/**
 * Bean自定义异常
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */
public class BeansException extends RuntimeException {
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
