package org.springframework.core;

/**
 * {@code Ordered} is an interface that can be implemented by the objects that should be <em>orderable</em>,
 * for example in the Collection
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public interface Ordered {

    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    /**
     * Get the order value of this object.
     *
     * @return order value
     */
    default int getOrder() {
        return 0;
    }
}
