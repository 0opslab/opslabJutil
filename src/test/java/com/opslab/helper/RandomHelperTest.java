package com.opslab.helper;

import com.opslab.helper.RandomHelper;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.opslab.helper.RandomHelper.integer;
import static org.junit.Assert.assertEquals;


@Ignore
public class RandomHelperTest {

    @Test
    public void testInteger() {
        for (int i = 0; i < 10; i++) {
            System.out.println(integer(0, 10));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(integer(20, 30));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(integer(100, 110));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(integer(1, 1000000000));
        }
    }

    @Test
    public void testUUid16(){
        for (int i = 0; i < 1000; i++) {
            System.out.println(RandomHelper.uuid16());
        }
    }


    @Test
    public void testInteger1() {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < 1000; i++) {
            int integer = RandomHelper.integer(1, 100);
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);

            } else {
                map.put(integer, 1);
            }
        }
        System.out.println(CollectionHelper.join(map, "\n", "="));
    }

    @Test
    public void testName() throws Exception {

        System.out.println(integer(30, 10));
        System.out.println(integer(0, 10));
        System.out.println(RandomHelper.number(10));
        System.out.println(RandomHelper.number(10));
        System.out.println(RandomHelper.string(10));
        System.out.println(RandomHelper.mixString(10));
        System.out.println(RandomHelper.lowerString(10));
        System.out.println(RandomHelper.upperString(10));
        System.out.println(RandomHelper.zeroString(10));
        System.out.println(RandomHelper.toFixdLengthString(123, 10));
        System.out.println(RandomHelper.toFixdLengthString(123L, 10));
        int[] in = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(RandomHelper.getNotSimple(in, 3));
    }

    @Test
    public void testUuid() {
        System.out.println(RandomHelper.uuid());
        System.out.println(RandomHelper.UUID());
        System.out.println(RandomHelper.squid("1234"));
        System.out.println(RandomHelper.squid("1234"));
        System.out.println(RandomHelper.squid("1234"));
        for (int i = 0; i < 10; i++) {
            Set<String> set = new HashSet<>(100000);
            for (int j = 0; j < 100000; j++) {
                set.add(RandomHelper.squid("1234"));
            }
            assertEquals("出现重复主键", 100000, set.size());
        }

    }

    @Test
    public void testRandomItem() {
        for (int j = 0; j < 10; j++) {
            Map<Integer, Integer> map = new HashMap();
            for (int i = 0; i < 1000000; i++) {
                Integer integer = RandomHelper.randomItem(new Integer[]{10, 30, 50});
                if (map.containsKey(integer)) {
                    map.put(integer, map.get(integer) + 1);
                } else {
                    map.put(integer, 1);
                }
            }
            int count = map.get(10) + map.get(30) + map.get(50);
            String str = "10/30/50 =" + map.get(10) + ":" + map.get(30) + ":" + map.get(50)
                    + "(" + count + ")";
            System.out.println(str);
        }
    }

    @Test
    public void testRandomItemRatio() {

        for (int j = 0; j < 10; j++) {
            Map<Integer, Integer> map = new HashMap();
            for (int i = 0; i < 1000000; i++) {
                double[] percentum = new double[]{0.6, 0.3, 0.1};
                Integer integer = RandomHelper.randomItem(new Integer[]{10, 30, 50}, percentum);
                if (map.containsKey(integer)) {
                    map.put(integer, map.get(integer) + 1);
                } else {
                    map.put(integer, 1);
                }
            }
            int count = map.get(10) + map.get(30) + map.get(50);
            String str = "10/30/50 =" + map.get(10) + ":" + map.get(30) + ":" + map.get(50)
                    + "(" + count + ") ->" +
                    StringHelper.formatNumber(new BigDecimal(map.get(10) / (float) count), "#.0000") + ":" +
                    StringHelper.formatNumber(new BigDecimal(map.get(30) / (float) count), "#.0000") + ":" +
                    StringHelper.formatNumber(new BigDecimal(map.get(50) / (float) count), "#.0000");
            System.out.println(str);
        }

        //
        System.out.println("测试万分之一的概率");
        for (int j = 0; j < 10; j++) {
            Map<Integer, Integer> map2 = new HashMap();
            for (int i = 0; i < 1000000; i++) {
                double[] percentum = new double[]{0.6, 0.4999, 0.0001};
                Integer integer = RandomHelper.randomItem(new Integer[]{10, 30, 50}, percentum);
                if (map2.containsKey(integer)) {
                    map2.put(integer, map2.get(integer) + 1);
                } else {
                    map2.put(integer, 1);
                }
            }
            int count = map2.get(10) + map2.get(30) + map2.get(50);
            String str = "10/30/50 =" + map2.get(10) + ":" + map2.get(30) + ":" + map2.get(50)
                    + "(" + count + ") ->" +
                    StringHelper.formatNumber(new BigDecimal(map2.get(10) / (float) count), "#.00000") + ":" +
                    StringHelper.formatNumber(new BigDecimal(map2.get(30) / (float) count), "#.00000") + ":" +
                    StringHelper.formatNumber(new BigDecimal(map2.get(50) / (float) count), "#.00000");
            System.out.println(str);
        }
        System.out.println("测试十万分之一的概率");
        for (int j = 0; j < 10; j++) {
            Map<Integer, Integer> map3 = new HashMap();
            for (int i = 0; i < 1000000; i++) {
                double[] percentum = new double[]{0.6, 0.49999, 0.00001};
                Integer integer = RandomHelper.randomItem(new Integer[]{10, 30, 50}, percentum);
                if (map3.containsKey(integer)) {
                    map3.put(integer, map3.get(integer) + 1);
                } else {
                    map3.put(integer, 1);
                }
            }
            int count = map3.get(10) + map3.get(30) + map3.get(50);
            String str = "10/30/50 =" + map3.get(10) + ":" + map3.get(30) + ":" + map3.get(50)
                    + "(" + count + ") ->" +
                    StringHelper.formatNumber(new BigDecimal(map3.get(10) / (float) count), "#.000000") + ":" +
                    StringHelper.formatNumber(new BigDecimal(map3.get(30) / (float) count), "#.000000") + ":" +
                    StringHelper.formatNumber(new BigDecimal(map3.get(50) / (float) count), "#.000000");
            System.out.println(str);
        }
    }

}