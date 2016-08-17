package com.opslab.util;

import com.opslab.util.valid;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class validTest extends TestCase {

    public void testIsValid() throws Exception {
        assertEquals(false, valid.valid(""));
        assertEquals(true, valid.valid("111"));
        assertEquals(true, valid.valid("111", "2222"));
        assertEquals(false, valid.valid("111", ""));
    }

    public void testIsValid1() throws Exception {
        assertEquals(true, valid.valid(new String("11")));

    }

    public void testIsValid2() throws Exception {
        Map map = new HashMap();
        assertEquals(false, valid.valid(map));
        map.put("1", "1");
        assertEquals(true, valid.valid(map));
        Map map1 = new HashMap();
        assertEquals(false, valid.valid(map, map1));
        map1.put("2", "2");
        assertEquals(true, valid.valid(map, map1));
    }

    public void testIsDate(){
        assertEquals(true,valid.isDate("2016-03-16 00:07:02",
                "yyyy-MM-dd HH:mm:ss"));
    }

    public void testSwith(){
        System.out.println(valid.valid(new Object()));
        System.out.println(valid.valid(""));
    }

}