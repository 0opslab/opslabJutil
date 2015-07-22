package evilp0s;

import javafx.scene.shape.VLineTo;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class ValidUtilTest extends TestCase {

    public void testIsValid() throws Exception {
        assertEquals(false,ValidUtil.isValid(""));
        assertEquals(true,ValidUtil.isValid("111"));
        assertEquals(true,ValidUtil.isValid("111", "2222"));
        assertEquals(false,ValidUtil.isValid("111",""));
    }

    public void testIsValid1() throws Exception {
        assertEquals(true,ValidUtil.isValid(new String("11")));

    }

    public void testIsValid2() throws Exception {
        Map map = new HashMap();
        assertEquals(false,ValidUtil.isValid(map));
        map.put("1","1");
        assertEquals(true, ValidUtil.isValid(map));
        Map map1 = new HashMap();
        assertEquals(false,ValidUtil.isValid(map,map1));
        map1.put("2","2");
        assertEquals(true,ValidUtil.isValid(map,map1));
    }


}