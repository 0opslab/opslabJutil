package com.opslab.util.algorithmImpl;

import org.junit.Test;
import com.opslab.util.TestUtil;

import java.io.File;

public class FileImplTest {

    @Test
    public void TestCpdetector() throws Exception {
        System.out.println(new FileImpl().guestFileEncoding(TestUtil.path + "GBK.txt"));
        System.out.println(new FileImpl().guestFileEncoding(TestUtil.path + "GBK.txt", 3));


        System.out.println(new FileImpl().guestFileEncoding(TestUtil.path + "UTF8.txt"));
        System.out.println(new FileImpl().guestFileEncoding(TestUtil.path + "UTF8.txt", 3));
    }
}