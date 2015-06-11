package evilp0s.algorithmImpl;

import junit.framework.TestCase;

import java.io.File;
import java.io.FilenameFilter;

public class FileEncodingUtilTest extends TestCase {

    public void testConvert() throws Exception {
        FileEncodingUtil.convert("C:\\Users\\Administrator\\Desktop\\1111.txt",
                "GBK", "UTF-8", new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith("txt");
                    }
                });
    }

    public void testConvert1() throws Exception {

    }

    public void testConvert2() throws Exception {

    }

    public void testConvert3() throws Exception {

    }

    public void testGetFileContentFromCharset() throws Exception {

    }

    public void testSaveFile2Charset() throws Exception {

    }
}