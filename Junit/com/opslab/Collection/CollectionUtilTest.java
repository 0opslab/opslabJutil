package com.opslab.collection;

import com.opslab.PrintUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

public class CollectionUtilTest extends TestCase {

    @Test
    public void testRemoveDuplicate() {
        List<String> list1 = new ArrayList<>();
        list1.add("AAA");
        list1.add("BBB");
        list1.add("CCC");
        list1.add("AAA");
        PrintUtil.print(list1);
        PrintUtil.print("====去重====");
        PrintUtil.print(CollectionUtil.removeDuplicate(list1));
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
        PrintUtil.print("=================测试List的集合相关的方法=================");
        PrintUtil.print("list1:" + list1);
        PrintUtil.print("list2:" + list2);
        List<String> userIntersection = CollectionUtil.intersection(list1, list2);
        PrintUtil.print("交集:" + userIntersection);
        PrintUtil.print("list1:" + list1);
        PrintUtil.print("list2:" + list2);


        PrintUtil.print("list1:" + list1);
        PrintUtil.print("list2:" + list2);
        List<String> userUnicon = CollectionUtil.unicon(list1, list2);
        PrintUtil.print("并集:" + userUnicon);
        PrintUtil.print("list1:" + list1);
        PrintUtil.print("list2:" + list2);

        PrintUtil.print("list1:" + list1);
        PrintUtil.print("list2:" + list2);
        List<String> subtract = CollectionUtil.subtract(list1, list2);
        PrintUtil.print("差集:" + subtract);
        PrintUtil.print("list1:" + list1);
        PrintUtil.print("list2:" + list2);


        //过滤函数
        PrintUtil.print("====================");
        PrintUtil.print("过滤前的原list" + list1);
        List<String> filter = CollectionUtil.Filter(list1, new ListFilter() {
            @Override
            public boolean filter(Object o) {
                return "AAA".equals((String) o);
            }
        });
        PrintUtil.print("过滤后结果:" + filter);
        PrintUtil.print("过滤后的原list" + list1);


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

        PrintUtil.print("=================测试Set的集合相关的方法=================");
        PrintUtil.print("set1:" + set1);
        PrintUtil.print("set2:" + set2);
        Set<String> set = CollectionUtil.intersection(set1, set2);
        PrintUtil.print("交集:" + set);
        PrintUtil.print("set1:" + set1);
        PrintUtil.print("set2:" + set2);

        PrintUtil.print("set1:" + set1);
        PrintUtil.print("set2:" + set2);
        Set<String> set3 = CollectionUtil.unicon(set1, set2);
        PrintUtil.print("并集:" + set3);
        PrintUtil.print("set1:" + set1);
        PrintUtil.print("set2:" + set2);

        PrintUtil.print("set1:" + set1);
        PrintUtil.print("set2:" + set2);
        Set<String> set4 = CollectionUtil.subtract(set1, set2);
        PrintUtil.print("差集:" + set4);
        PrintUtil.print("set1:" + set1);
        PrintUtil.print("set2:" + set2);

        PrintUtil.print("====================");
        PrintUtil.print("过滤前:" + set1);
        Set<String> filter = CollectionUtil.Filter(set, new SetFilter() {
            @Override
            public boolean filter(Object o) {
                return !"AAA".equals((String) o);
            }
        });
        PrintUtil.print("过滤结果:" + filter);
        PrintUtil.print("过滤结果:" + set1);
    }

    @Test
    public void testMap() throws Exception {
        PrintUtil.print("=================测试Map的集合相关的方法=================");
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
        PrintUtil.print("原map:" + map);
        PrintUtil.print("原map1:" + map1);
        PrintUtil.print("交集:" + map2);
        //原方法不应该修改原集合
        PrintUtil.print("原map:" + map);
        PrintUtil.print("原map1:" + map1);

        //并集
        Map<String,String> map3 = CollectionUtil.unicon(map, map1);
        PrintUtil.print("原map:" + map);
        PrintUtil.print("原map1:" + map1);
        PrintUtil.print("并集:" + map3);
        //原方法不应该修改原集合
        PrintUtil.print("原map:" + map);
        PrintUtil.print("原map1:" + map1);

        //差集
        Map<String,String> map4 = CollectionUtil.subtract(map, map1);
        PrintUtil.print("原map:" + map);
        PrintUtil.print("原map1:" + map1);
        PrintUtil.print("差集:" + map4);
        //原方法不应该修改原集合
        PrintUtil.print("原map:" + map);
        PrintUtil.print("原map1:" + map1);

        //过滤
        PrintUtil.print("原map1:" + map1);
        Map<String,String> filter = CollectionUtil.Filter(map1, new MapFilter() {
            @Override
            public boolean filter(Object o) {
                Map.Entry<String,String> entry = (Map.Entry<String,String>) o;
                return !"CCC1".equals(entry.getValue());
            }
        });
        PrintUtil.print("过滤结果:" + filter);
        PrintUtil.print("原map1:" + map1);
    }


}