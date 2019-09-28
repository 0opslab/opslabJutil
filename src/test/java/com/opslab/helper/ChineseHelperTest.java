package com.opslab.helper;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChineseHelperTest {

    @Test
    public void test(){
        System.out.println(ChineseHelper.transIDCard15to18("370986890623212"));
        System.out.println(ChineseHelper.transIDCard15to18("370725881105149"));
    }

}