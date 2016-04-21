package com.opslab;

import org.junit.Test;


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


}