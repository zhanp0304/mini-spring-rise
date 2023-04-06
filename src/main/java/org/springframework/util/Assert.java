package org.springframework.util;

import org.springframework.lang.Nullable;

/**
 * Description
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/6
 */
public class Assert {
    /**
     * Assert that an object is not {@code null}.
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
