package com.opslab;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class SecUtilTest {

    @Test
    public void testMd5() throws Exception {
        String str1 = "123456";
        String str2 = SecUtil.md5(str1);
        assertEquals("计算错误", "e10adc3949ba59abbe56e057f20f883e", str2);
    }

    @Test
    public void testFileMD5() {
        String file = System.getProperty("user.dir") + "/Junit/Resource/cmdexe";
        assertEquals("ad7b9c14083b52bc532fba5948342b98", SecUtil.FileMD5(new File(file)));
    }
}