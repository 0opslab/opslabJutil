package com.opslab.util;

import org.junit.Test;

import java.io.File;

public class ZIPUtilTest  {

    @Test
    public void testDeCompress() throws Exception {
        String file    = SysUtil.CURRENT_USER_DIR + "/src/test/java/model";
        String zipFile = TestUtil.path + "temp/test.zip";
        ZIPUtil.deCompress(new File(file), zipFile);


    }

    @Test
    public void testUnCompress() throws Exception {
        String zipFile = TestUtil.path + "temp/test.zip";
        if (!(new File(zipFile).exists())) {
            testDeCompress();
        }
        ZIPUtil.unCompress(new File(zipFile), TestUtil.path+"temp/unzip/");
    }
}