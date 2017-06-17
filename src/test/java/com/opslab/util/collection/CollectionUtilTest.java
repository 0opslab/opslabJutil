package com.opslab.util.collection;

import junit.framework.TestCase;
import com.opslab.temp.model.User;
import org.junit.Test;

import java.util.*;

public class CollectionUtilTest extends TestCase {

    /**
     * 测试利用list转set或set转list的方法是否能解决深拷贝的问题
     * <p/>
     * 测试结果:不能解决深拷贝的问题
     */
    @Test
    public void testDeepCopy() {
        List<User> list1 = new ArrayList<>();
        User       user1 = new User("111", "AAA");
        User       user2 = new User("222", "BBB");
        User       user3 = new User("333", "CCC");
        list1.add(user1);
        list1.add(user2);
        list1.add(user3);
        System.out.println("list:" + list1);
        user1.setUserId("444");
        System.out.println("list:" + list1);

        Set<User> set = new HashSet<>(list1);
        System.out.println("Set:" + set);

        System.out.println("==============");
        user2.setUserId("5555");
        list1.add(new User("666", "EEE"));
        System.out.println("list:" + list1);
        System.out.println("Set:" + set);


    }

    @Test
    public void testRemoveDuplicate() {
        List<String> list1 = new ArrayList<>();
        list1.add("AAA");
        list1.add("BBB");
        list1.add("CCC");
        list1.add("AAA");
        System.out.println(list1);
        System.out.println("====去重====");
        System.out.println(CollectionUtil.removeDuplicate(list1));
    }

    @Test
    public void testList() throws Exception {
        List<String> list1 = new ArrayList<>();
        list1.add("AAA");
        list1.add("BBB");
        list1.add("CCC");

        List<String> list2 = new ArrayList<>();
        list2.add("BBB");
        list2.add("CCC");
        list2.add("DDD");


        //测试复合类型(复合类型需要实现equals方法)
        System.out.println("=================测试List的集合相关的方法=================");
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        List<String> userIntersection = CollectionUtil.intersection(list1, list2);
        System.out.println("交集:" + userIntersection);
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);


        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        List<String> userUnicon = CollectionUtil.unicon(list1, list2);
        System.out.println("并集:" + userUnicon);
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);

        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        List<String> subtract = CollectionUtil.subtract(list1, list2);
        System.out.println("差集:" + subtract);
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);


        //过滤函数
        System.out.println("====================");
        System.out.println("过滤前的原list" + list1);
        List<String> filter = CollectionUtil.filter(list1, new ListFilter() {
            @Override
            public boolean filter(Object o) {
                return "AAA".equals((String) o);
            }
        });
        System.out.println("过滤后结果:" + filter);
        System.out.println("过滤后的原list" + list1);


    }

    @Test
    public void testSet() throws Exception {
        Set<String> set1 = new HashSet<>();
        set1.add("AAA");
        set1.add("BBB");
        set1.add("CCC");

        Set<String> set2 = new HashSet<>();
        set2.add("BBB");
        set2.add("CCC");
        set2.add("DDD");

        System.out.println("=================测试Set的集合相关的方法=================");
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        Set<String> set = CollectionUtil.intersection(set1, set2);
        System.out.println("交集:" + set);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);

        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        Set<String> set3 = CollectionUtil.unicon(set1, set2);
        System.out.println("并集:" + set3);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);

        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        Set<String> set4 = CollectionUtil.subtract(set1, set2);
        System.out.println("差集:" + set4);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);

        System.out.println("====================");
        System.out.println("过滤前:" + set1);
        Set<String> filter = CollectionUtil.filter(set, new SetFilter() {
            @Override
            public boolean filter(Object o) {
                return !"AAA".equals((String) o);
            }
        });
        System.out.println("过滤结果:" + filter);
        System.out.println("过滤结果:" + set1);
    }

    @Test
    public void testMap() throws Exception {
        System.out.println("=================测试Map的集合相关的方法=================");
        Map<String,String> map = new HashMap();
        map.put("AAA", "AAA1");
        map.put("BBB", "BBB1");
        map.put("CCC", "CCC1");
        map.put("DDD", "DDD1");

        Map<String,String> map1 = new HashMap();
        map1.put("BBB", "BBB1");
        map1.put("CCC", "CCC1");
        map1.put("DDD", "DDD1");
        map1.put("EEE", "EEE1");

        //交集
        Map<String,String> map2 = CollectionUtil.intersection(map, map1);
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);
        System.out.println("交集:" + map2);
        //原方法不应该修改原集合
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);

        //并集
        Map<String,String> map3 = CollectionUtil.unicon(map, map1);
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);
        System.out.println("并集:" + map3);
        //原方法不应该修改原集合
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);

        //差集
        Map<String,String> map4 = CollectionUtil.subtract(map, map1);
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);
        System.out.println("差集:" + map4);
        //原方法不应该修改原集合
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);

        //过滤
        System.out.println("原map1:" + map1);
        Map<String,String> filter = CollectionUtil.Filter(map1, new MapFilter() {
            @Override
            public boolean filter(Object o) {
                Map.Entry<String,String> entry = (Map.Entry<String,String>) o;
                return !"CCC1".equals(entry.getValue());
            }
        });
        System.out.println("过滤结果:" + filter);
        System.out.println("原map1:" + map1);
    }

    @Test
    public void testJoin(){
        List<String> list1 = new ArrayList<>();
        list1.add("AAA");
        list1.add("BBB");
        list1.add("CCC");
        list1.add("AAA");

        System.out.println(CollectionUtil.join(list1,"-"));

        Set<String> set1 = new HashSet<>();
        set1.add("AAA");
        set1.add("BBB");
        set1.add("CCC");

        System.out.println(CollectionUtil.join(set1,"-"));

        Map<String,String> map = new HashMap();
        map.put("AAA", "AAA1");
        map.put("BBB", "BBB1");
        map.put("CCC", "CCC1");
        map.put("DDD", "DDD1");

        System.out.println(CollectionUtil.keyJoin(map,"="));
        System.out.println(CollectionUtil.valueJoin(map,"="));
    }

}