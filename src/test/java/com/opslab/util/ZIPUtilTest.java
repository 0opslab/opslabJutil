package com.opslab.util;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class ZIPUtilTest {
    String path = TestUtil.path;

    @Test
    @Ignore
    public void testDeCompress() throws Exception {
        String file = path+ "/src/test/java";
        String zipFile = TestUtil.path + "temp/test.zip";
        ZIPUtil.deCompress(new File(file), zipFile);


        File file1 = new File(path + "/src/test/java/com/opslab/helper/FileHelperTest.java");
        ZIPUtil.deCompress(file1, TestUtil.path + "temp/test1.zip");

    }

    @Test
    @Ignore
    public void testUnCompress() throws Exception {
        String zipFile = TestUtil.path + "temp/test.zip";
        if (!(new File(zipFile).exists())) {
            testDeCompress();
        }
        ZIPUtil.unCompress(new File(zipFile), TestUtil.path + "temp/unzip/");
    }
}