package com.opslab.util;


/**
 * 断言
 */
public class AssertUtil {

    /**
     * 判断对象是否为空
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
