package com.opslab.util;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class CheckUtilTest extends TestCase {

    public void testIsValid() throws Exception {
        assertEquals(false, CheckUtil.valid(""));
        assertEquals(true, CheckUtil.valid("111"));
        assertEquals(true, CheckUtil.valid(new String[]{"111", "2222"}));
        assertEquals(false, CheckUtil.valid(new String[]{"111", ""}));
    }

    public void testIsValid1() throws Exception {
        assertEquals(true, CheckUtil.valid(new String("11")));

    }

    public void testIsValid2() throws Exception {
        Map map = new HashMap();
        assertEquals(false, CheckUtil.valid(map));
        map.put("1", "1");
        assertEquals(true, CheckUtil.valid(map));
        Map map1 = new HashMap();
        assertEquals(false, CheckUtil.valid(map, map1));
        map1.put("2", "2");
        assertEquals(true, CheckUtil.valid(map, map1));
    }

    public void testIsDate() {
        assertEquals(true, CheckUtil.isDate("2016-03-16 00:07:02",
                "yyyy-MM-dd HH:mm:ss"));
    }

    public void testSwith() {
        System.out.println(CheckUtil.valid(new Object()));
        System.out.println(CheckUtil.valid(""));
    }

}