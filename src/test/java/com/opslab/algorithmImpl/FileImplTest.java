package com.opslab.algorithmImpl;

import org.junit.Test;
import test.TestUtil;

import java.io.File;

public class FileImplTest {

    @Test
    public void TestCpdetector() throws Exception {
        System.out.println(FileImpl.simpleEncoding(TestUtil.path + "GBK.txt"));
        System.out.println(FileImpl.cpdetector((new File(TestUtil.path + "GBK.txt")).toURL()));
        System.out.println(new FileImpl().guestFileEncoding(TestUtil.path + "GBK.txt"));
        System.out.println(new FileImpl().guestFileEncoding(TestUtil.path + "GBK.txt", 3));


        System.out.println(FileImpl.simpleEncoding(TestUtil.path + "UTF8.txt"));
        System.out.println(FileImpl.cpdetector((new File(TestUtil.path + "UTF8.txt")).toURL()));
        System.out.println(new FileImpl().guestFileEncoding(TestUtil.path + "UTF8.txt"));
        System.out.println(new FileImpl().guestFileEncoding(TestUtil.path + "UTF8.txt", 3));
    }
}