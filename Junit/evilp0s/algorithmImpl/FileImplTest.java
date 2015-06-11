package evilp0s.algorithmImpl;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class FileImplTest {

    @Test
    public void TestCpdetector() throws Exception {
        System.out.println(FileImpl.SimpleEncoding("c:/windows/WindowsUpdate.log"));
        System.out.println(FileImpl.cpdetector((new File("c:/windows/WindowsUpdate.log")).toURL()));
        System.out.println(new FileImpl().guestFileEncoding("c:/windows/WindowsUpdate.log"));
        System.out.println(new FileImpl().guestFileEncoding("c:/windows/WindowsUpdate.log",3));


        System.out.println(FileImpl.SimpleEncoding("C:/Windows/Tasks/SCHEDLGU.TXT"));
        System.out.println(FileImpl.cpdetector((new File("C:/Windows/Tasks/SCHEDLGU.TXT")).toURL()));
        System.out.println(FileImpl.guestFileEncoding("C:/Windows/Tasks/SCHEDLGU.TXT"));
        System.out.println(FileImpl.guestFileEncoding("C:/Windows/Tasks/SCHEDLGU.TXT", 3));
    }
}