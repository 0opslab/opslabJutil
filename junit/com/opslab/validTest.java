package com.opslab;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class validTest extends TestCase {

    public void testIsValid() throws Exception {
        assertEquals(false, valid.isValid(""));
        assertEquals(true, valid.isValid("111"));
        assertEquals(true, valid.isValid("111", "2222"));
        assertEquals(false, valid.isValid("111", ""));
    }

    public void testIsValid1() throws Exception {
        assertEquals(true, valid.isValid(new String("11")));

    }

    public void testIsValid2() throws Exception {
        Map map = new HashMap();
        assertEquals(false, valid.isValid(map));
        map.put("1", "1");
        assertEquals(true, valid.isValid(map));
        Map map1 = new HashMap();
        assertEquals(false, valid.isValid(map, map1));
        map1.put("2", "2");
        assertEquals(true, valid.isValid(map, map1));
    }

    public void testIsDate(){
        assertEquals(true,valid.isDate("2016-03-16 00:07:02",
                "yyyy-MM-dd HH:mm:ss"));
    }

}