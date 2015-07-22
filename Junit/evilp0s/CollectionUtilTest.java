package evilp0s;

import junit.framework.TestCase;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtilTest extends TestCase {

    public void testList() throws Exception {
        List<String> list1 = new ArrayList<>();
        list1.add("AAA");
        list1.add("BBB");
        list1.add("CCC");

        List<String> list2 = new ArrayList<>();
        list2.add("BBB");
        list2.add("CCC");
        list2.add("DDD");

        //测试基础类型
        List<String> list = CollectionUtil.intersection(list1, list2);
        List<String> uniconlist = CollectionUtil.unicon(list1, list2);
        PrintUtil.print("list1:" + list1);
        PrintUtil.print("list2:" + list2);
        PrintUtil.print("交集:" + list);
        PrintUtil.print("并集:" + uniconlist);

        List<User> listuser1 = new ArrayList<>();
        User user1 = new User("111", "AAA");
        User user2 = new User("222", "BBB");
        User user3 = new User("333", "CCC");
        listuser1.add(user1);
        listuser1.add(user2);
        listuser1.add(user3);

        List<User> listuser2 = new ArrayList<>();
        User user4 = new User("111", "AAA");
        User user5 = new User("444", "DDD");
        User user6 = new User("555", "EEE");
        listuser2.add(user4);
        listuser2.add(user5);
        listuser2.add(user6);

        //测试复合类型(复合类型需要实现equals方法)
        List<User> userIntersection = CollectionUtil.intersection(listuser1, listuser2);
        List<User> userUnicon = CollectionUtil.unicon(listuser1, listuser2);
        PrintUtil.print("listuser1:" + listuser1);
        PrintUtil.print("listuser2:" + listuser2);
        PrintUtil.print("交集:" + userIntersection);
        PrintUtil.print("并集:" + userUnicon);

    }

    public void testSet() throws Exception {
        Set<String> set1 = new HashSet<String>();
        set1.add("AAA");
        set1.add("BBB");
        set1.add("CCC");

        Set<String> set2 = new HashSet<String>();
        set2.add("BBB");
        set2.add("CCC");
        set2.add("DDD");

        Set<String> set = CollectionUtil.intersection(set1, set2);
        PrintUtil.print("set1:" + set1);
        PrintUtil.print("set2:" + set2);
        PrintUtil.print("交集:" + set);

    }

    public void testMap() throws Exception {

    }


}