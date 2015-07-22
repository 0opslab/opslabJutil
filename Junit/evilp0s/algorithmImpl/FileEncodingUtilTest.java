package evilp0s.algorithmImpl;

import evilp0s.SysUtil;
import junit.framework.TestCase;

import java.io.File;
import java.io.FilenameFilter;

public class FileEncodingUtilTest extends TestCase {

    public void testConvert() throws Exception {
        String file = SysUtil.CURRENT_USER_DIR + "/Junit/Resource/GBKTOUTF8.txt";
        FileEncodingUtil.convert(file,
                "GBK", "UTF-8", new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith("txt");
                    }
                });
    }

    public void testConvert1() throws Exception {
        String file = SysUtil.CURRENT_USER_DIR + "/Junit/Resource/GBKTOUTF8.txt";
        FileEncodingUtil.convert(file,
                "UTF-8", "GBK", new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith("txt");
                    }
                });
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