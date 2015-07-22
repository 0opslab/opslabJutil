package evilp0s;

import org.junit.Test;

import java.util.*;

public class PrintUtilTest {
    @Test
    public void testPrint() throws Exception {
        List list = new ArrayList<String>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        PrintUtil.print(list);

        Map<String,String> map = new HashMap<String,String>();
        map.put("1","test1");
        map.put("2","test2");
        map.put("3","test3");
        map.put("4","test4");
        PrintUtil.print(map);

        Vector<String> all = new Vector<String>();
        all.add("hello");
        all.add("world");
        PrintUtil.print(all);
    }


}