package evilp0s;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

public class ZIPUtilTest extends SupportTest {

    @Test
    public void testDeCompress() throws Exception {
        ZIPUtil.deCompress(new File("c:/test"),"c:/test.zip");
    }

    @Test
    public void testUnCompress() throws Exception {
        ZIPUtil.unCompress(new File("c:/test.zip"),"c:/1");
    }
}