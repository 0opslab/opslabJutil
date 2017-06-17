package com.opslab.util;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * 测试异常工具类
 */
public class ExceptionUtilTest  {
    @Test
    public void testStackTraceToString() throws Exception {
        try{
            int i=1/0;
            System.out.println(i);
        }catch (Exception e){
            System.out.println("========");
            System.out.println(ExceptionUtil.stackTraceToString(e,"com.opslab"));
            System.out.println(ExceptionUtil.stackTraceToString(e));
        }
    }

}