package com.opslab.util;

import com.opslab.util.RandomUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;


public class RandomUtilTest {

    @Test
    public void testInteger(){
        for(int i=0;i<10;i++){
            System.out.println(RandomUtil.integer(0,10));
        }
        for(int i=0;i<10;i++){
            System.out.println(RandomUtil.integer(20,30));
        }

        for(int i=0;i<10;i++){
            System.out.println(RandomUtil.integer(100,110));
        }
    }

    @Test
    public void testName() throws Exception {

        System.out.println(RandomUtil.integer(30,10));
        System.out.println(RandomUtil.integer(0,10));
        System.out.println(RandomUtil.number(10));
        System.out.println(RandomUtil.number(10));
        System.out.println(RandomUtil.String(10));
        System.out.println(RandomUtil.MixString(10));
        System.out.println(RandomUtil.LowerString(10));
        System.out.println(RandomUtil.UpperString(10));
        System.out.println(RandomUtil.ZeroString(10));
        System.out.println(RandomUtil.toFixdLengthString(123, 10));
        System.out.println(RandomUtil.toFixdLengthString(123L, 10));
        int[] in = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(RandomUtil.getNotSimple(in, 3));
    }

    @Test
    public void testUuid(){
        System.out.println(RandomUtil.uuid());
        System.out.println(RandomUtil.UUID());
        System.out.println(RandomUtil.squid());
        System.out.println(RandomUtil.squid());
        System.out.println(RandomUtil.squid());
        for(int i=0;i<10;i++){
            Set<String> set = new HashSet<>(100000);
            for(int j=0;j<100000;j++){
                set.add(RandomUtil.squid());
            }
            assertEquals("出现重复主键",100000,set.size());
        }

    }

}