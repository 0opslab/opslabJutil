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
            e.printStackTrace();
            Thread.sleep(10);
            System.out.println("========");
            System.out.println(ExceptionUtil.stackTraceToString(e,"com.opslab"));
        }
    }

}