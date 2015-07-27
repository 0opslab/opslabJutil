package evilp0s;

import model.User;
import org.junit.Test;

import java.util.*;

public class PrintUtilTest {
    @Test
    public void testPrint() throws Exception {
        List list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        PrintUtil.print(list);


        Map<String,String> map = new HashMap<>();
        map.put("1", "test1");
        map.put("2", "test2");
        map.put("3", "test3");
        map.put("4", "test4");
        PrintUtil.print(map);

        Vector<String> all = new Vector<>();
        all.add("hello");
        all.add("world");
        PrintUtil.print(all);
        PrintUtil.print(all.elements());
    }


    @Test
    public void testArr(){
        byte[] bytes = new byte[]{1,2,3};
        PrintUtil.print(bytes);
        char[] chars = new char[]{'a','b','中','文'};
        PrintUtil.print(chars);
        int[] ints = new int[]{1,2,34,};
        PrintUtil.print(ints);
        User[] users = new User[]{new User("11","aa"),new User("22","bb")};
        PrintUtil.print(users);
    }



}