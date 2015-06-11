package evilp0s;

import org.junit.Test;

public class ProUtilTest {

    @Test
    public void testKey() throws Exception {
        System.out.println(System.getProperties());
        System.out.println(ProUtil.key("user.name"));
        System.out.println(ProUtil.key("file.encoding"));
    }

    @Test
    public void testPro() throws Exception{
        System.out.println(ProUtil.key("user.dir"));
        System.out.println(ProUtil.GetValueByKey("Junit/Test.properties", "test"));
        System.out.println(ProUtil.GetAllProperties("Junit/Test.properties"));
        ProUtil.WriteProperties("Junit/Test.properties", "long", "212");
        ProUtil.WriteProperties("Junit/Test.properties", "test", "212");
    }
}