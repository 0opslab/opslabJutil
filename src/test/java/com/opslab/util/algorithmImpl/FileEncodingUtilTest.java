package com.opslab.util.algorithmImpl;

import com.opslab.Opslab;
import junit.framework.TestCase;
import com.opslab.util.TestUtil;

import java.io.File;
import java.io.FilenameFilter;

public class FileEncodingUtilTest extends TestCase {

    public void testConvert() {
        String file = TestUtil.path + "/text/GBKTOUTF8.txt";
        FileEncodingUtil.convert(file, Opslab.GBK, Opslab.UTF_8);
    }

    public void testConvert1() {
        String file = TestUtil.path + "/text/GBKTOUTF8.txt";
        FileEncodingUtil.convert(file, Opslab.UTF_8, Opslab.GBK, new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("txt");
            }
        });
    }

    public void testConvert2() {
        String file = TestUtil.path + "/text/GBKTOUTF8.txt";
        FileEncodingUtil.convert(new File(file), Opslab.GBK, Opslab.UTF_8);
    }
}