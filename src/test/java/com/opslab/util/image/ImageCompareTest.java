package com.opslab.util.image;

import junit.framework.TestCase;
import com.opslab.util.TestUtil;

import java.io.File;


public class ImageCompareTest extends TestCase {

    public void testCompareImage() throws Exception {
        String path = TestUtil.path+"/image";
        float v = ImageCompare.compareImage(new File(path + "/1.jpg"), new File(path + "/1.jpg"));
        System.out.println(v);
    }
}