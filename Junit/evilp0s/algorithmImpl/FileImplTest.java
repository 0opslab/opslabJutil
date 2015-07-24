package evilp0s.algorithmImpl;

import org.junit.Test;

import java.io.File;

public class FileImplTest {

    @Test
    public void TestCpdetector() throws Exception {
        String path = System.getProperty("user.dir") + "/Junit/Resource";
        System.out.println(FileImpl.simpleEncoding(path + "/GBK.txt"));
        System.out.println(FileImpl.cpdetector((new File(path + "/GBK.txt")).toURL()));
        System.out.println(new FileImpl().guestFileEncoding(path + "/GBK.txt"));
        System.out.println(new FileImpl().guestFileEncoding(path + "/GBK.txt", 3));


        System.out.println(FileImpl.simpleEncoding(path + "/UTF8.txt"));
        System.out.println(FileImpl.cpdetector((new File(path + "/UTF8.txt")).toURL()));
        System.out.println(new FileImpl().guestFileEncoding(path + "/UTF8.txt"));
        System.out.println(new FileImpl().guestFileEncoding(path + "/UTF8.txt", 3));
    }
}