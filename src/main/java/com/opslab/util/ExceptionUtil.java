package com.opslab.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常处理的工具类
 */
public class ExceptionUtil {
    /**
     * 只返回指定包中的异常堆栈信息
     *
     * @param e
     * @param packageName
     * @return
     */
    public final static String stackTraceToString(Throwable e, String packageName) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        String[] arrs = str.split("\n");
        StringBuffer sbuf = new StringBuffer();
        sbuf.append(arrs[0] + "\n");
        for (int i = 0; i < arrs.length; i++) {
            String temp = arrs[i];
            if (temp != null && temp.indexOf(packageName) > 0) {
                sbuf.append(temp + "\n");
            }
        }
        return sbuf.toString();
    }
}
