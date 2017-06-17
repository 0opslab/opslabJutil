package com.opslab.util.algorithmImpl;

import junit.framework.TestCase;
import com.opslab.util.TestUtil;

import java.io.File;
import java.io.FilenameFilter;

public class FileEncodingUtilTest extends TestCase {

    public void testConvert()   {
        String file = TestUtil.path+ "GBKTOUTF8.txt";
        FileEncodingUtil.convert(file, "GBK", "UTF-8");
    }

    public void testConvert1() {
        String file = TestUtil.path + "GBKTOUTF8.txt";
        FileEncodingUtil.convert(file, "UTF-8", "GBK", new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.endsWith("txt");
                    }
                });
    }

    public void testConvert2(){
        String file = TestUtil.path + "GBKTOUTF8.txt";
        FileEncodingUtil.convert(new File(file),"GBK", "UTF-8");
    }
}