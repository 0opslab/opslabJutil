package evilp0s;

import org.junit.Test;

import java.nio.charset.Charset;

public class ProUtilTest {

    @Test
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
    public void testKey() throws Exception {
        System.out.println(System.getProperties());
        System.out.println(ProUtil.key("user.name"));
        System.out.println(ProUtil.key("file.encoding"));
    }

    @Test
    public void testPro() throws Exception {
        String path = ProUtil.key("user.dir") + "/Junit/Resource/Test.properties";
        System.out.println(ProUtil.key("user.dir"));
        System.out.println(ProUtil.GetValueByKey(path, "test"));
        System.out.println(ProUtil.GetAllProperties(path));
        ProUtil.WriteProperties(path, "long", "212");
        ProUtil.WriteProperties(path, "test", "212");
        ProUtil.WriteProperties(path, "test2", "中文测试");
        ProUtil.WriteProperties(path, "test3", "中文测试1");
        System.out.println(ProUtil.GetAllProperties(path));
    }
}