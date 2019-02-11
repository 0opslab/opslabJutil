package com.opslab.helper;

import com.opslab.functions.ObjectHandler;
import com.opslab.functions.ObjectProcess;
import com.opslab.temp.model.User;
import com.opslab.util.FileUtil;
import com.opslab.util.TestUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * 测试集合对象的一些助手工具方法
 */
@Ignore
public class CollectionHelperTest {
    String path = TestUtil.path;

    @Test
    public void handler() {
        String file = path + "/text/EnglishWrite.txt";
        List<String> lines = FileUtil.lines(new File(file));
        //如下所示，可以传递一个实现特定方法的类，以实现类似js中callback方法形式的编码风格
        CollectionHelper.handler(lines, new ObjectHandler<String>() {
            @Override
            public void handler(String line) {
                System.out.println("handler line:" + line);
            }
        });

//        CollectionHelper.handler(null,null);
    }

    @Test
    public void process() {
        String file = path + "/text/EnglishWrite.txt";

        List<String> lines = FileUtil.lines(new File(file));

        //返回当长度大于15的行
        Set<String> result = new HashSet<String>();
        CollectionHelper.process(lines, result, new ObjectProcess<String, String>() {
            @Override
            public String process(String s) {
                if (s != null && s.length() > 15) {
                    return s;
                }
                return null;
            }
        });

        System.out.println("====");
        CollectionHelper.handler(result, new ObjectHandler<String>() {
            @Override
            public void handler(String s) {
                System.out.println(s);
            }
        });
    }

    /**
     * 测试利用list转set或set转list的方法是否能解决深拷贝的问题
     * <p/>
     * 测试结果:不能解决深拷贝的问题
     */
    @Test
    public void testDeepCopy() {
        List<User> list1 = new ArrayList<>();
        User user1 = new User("111", "AAA");
        User user2 = new User("222", "BBB");
        User user3 = new User("333", "CCC");
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
        System.out.println(CollectionHelper.removeDuplicate(list1));
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
        List<String> userIntersection = CollectionHelper.intersection(list1, list2);
        System.out.println("交集:" + userIntersection);
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);


        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        List<String> userUnicon = CollectionHelper.unicon(list1, list2);
        System.out.println("并集:" + userUnicon);
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);

        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        List<String> subtract = CollectionHelper.subtract(list1, list2);
        System.out.println("差集:" + subtract);
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);


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
        Set<String> set = CollectionHelper.intersection(set1, set2);
        System.out.println("交集:" + set);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);

        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        Set<String> set3 = CollectionHelper.unicon(set1, set2);
        System.out.println("并集:" + set3);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);

        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        Set<String> set4 = CollectionHelper.subtract(set1, set2);
        System.out.println("差集:" + set4);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);


    }

    @Test
    public void testMap() throws Exception {
        System.out.println("=================测试Map的集合相关的方法=================");
        Map<String, String> map = new HashMap();
        map.put("AAA", "AAA1");
        map.put("BBB", "BBB1");
        map.put("CCC", "CCC1");
        map.put("DDD", "DDD1");

        Map<String, String> map1 = new HashMap();
        map1.put("BBB", "BBB1");
        map1.put("CCC", "CCC1");
        map1.put("DDD", "DDD1");
        map1.put("EEE", "EEE1");

        //交集
        Map<String, String> map2 = CollectionHelper.intersection(map, map1);
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);
        System.out.println("交集:" + map2);
        //原方法不应该修改原集合
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);

        //并集
        Map<String, String> map3 = CollectionHelper.unicon(map, map1);
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);
        System.out.println("并集:" + map3);
        //原方法不应该修改原集合
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);

        //差集
        Map<String, String> map4 = CollectionHelper.subtract(map, map1);
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);
        System.out.println("差集:" + map4);
        //原方法不应该修改原集合
        System.out.println("原map:" + map);
        System.out.println("原map1:" + map1);


    }

    @Test
    public void testJoin() {
        List<String> list1 = new ArrayList<>();
        list1.add("AAA");
        list1.add("BBB");
        list1.add("CCC");
        list1.add("AAA");

        System.out.println(CollectionHelper.join(list1, "-"));

        Set<String> set1 = new HashSet<>();
        set1.add("AAA");
        set1.add("BBB");
        set1.add("CCC");

        System.out.println(CollectionHelper.join(set1, "-"));

        Map<String, String> map = new HashMap();
        map.put("AAA", "AAA1");
        map.put("BBB", "BBB1");
        map.put("CCC", "CCC1");
        map.put("DDD", "DDD1");

        System.out.println(CollectionHelper.keyJoin(map, "="));
        System.out.println(CollectionHelper.valueJoin(map, "="));
    }

}