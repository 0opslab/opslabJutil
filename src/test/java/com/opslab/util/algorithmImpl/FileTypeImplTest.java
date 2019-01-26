package com.opslab.util.algorithmImpl;

import junit.framework.TestCase;
import org.junit.Test;
import com.opslab.util.TestUtil;

import java.io.File;

public class FileTypeImplTest extends TestCase {

    @Test
    public void testFileType() {
        assertEquals("gif", FileTypeImpl.getFileType(new File(TestUtil.path + "/image/ali.gif")));
        assertEquals("png", FileTypeImpl.getFileType(new File(TestUtil.path + "/image/tgepng")));
    }

}