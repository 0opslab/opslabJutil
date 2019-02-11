package com.opslab.util;

import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 测试异常工具类
 */
public class ExceptionUtilTest {
    @Test
    @Ignore
    public void testStackTraceToString() throws Exception {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println(ExceptionUtil.stackTraceToString(e, "com.opslab"));
            System.out.println(ExceptionUtil.stackTraceToString(e));
        }
    }

}