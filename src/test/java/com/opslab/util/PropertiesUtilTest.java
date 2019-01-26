package com.opslab.util;

import org.junit.Ignore;
import org.junit.Test;

import java.nio.charset.Charset;

public class PropertiesUtilTest {

    @Test
    @Ignore
    public void testmain() {
        //Win7默认会都会输出GBK(不过会应为应用程序的编码会发生相应的变化)
        System.out.println("File encoding:" + System.getProperty("file.encoding"));
        System.out.println("Default Charset:" + Charset.defaultCharset());
        System.out.println("os.arch:" + System.getProperty("os.arch"));
        System.out.println("os.version:" + System.getProperty("os.version"));
        System.out.println("os.name:" + System.getProperty("os.name"));
        System.out.println("sun.desktop:" + System.getProperty("sun.desktop"));
    }

    @Test
    @Ignore
    public void testKey() throws Exception {
        System.out.println(System.getProperties());
        System.out.println(PropertiesUtil.key("user.name"));
        System.out.println(PropertiesUtil.key("file.encoding"));
    }

    @Test
    @Ignore
    public void testPro() throws Exception {
        String path = TestUtil.path + "/text/Test.properties";
        System.out.println(PropertiesUtil.key("user.dir"));
        System.out.println(PropertiesUtil.GetValueByKey(path, "test"));
        System.out.println(PropertiesUtil.GetAllProperties(path));
        PropertiesUtil.WriteProperties(path, "long", "212");
        PropertiesUtil.WriteProperties(path, "test", "212");
        PropertiesUtil.WriteProperties(path, "test2", "中文测试");
        PropertiesUtil.WriteProperties(path, "test3", "中文测试1");
        System.out.println(PropertiesUtil.GetAllProperties(path));
    }
}