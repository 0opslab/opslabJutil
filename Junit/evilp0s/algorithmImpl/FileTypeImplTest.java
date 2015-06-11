package evilp0s.algorithmImpl;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

public class FileTypeImplTest extends TestCase {

    @Test
    public void testFileType(){
        System.out.println("测试文件类型");
        System.out.println(FileTypeImpl.getFileType(new File("D:/picture/1.jpg")));
        System.out.println(FileTypeImpl.getFileType(new File("c:/Python27/python.exe")));
    }

}