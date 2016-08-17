package com.opslab.util;


import com.opslab.util.FilePathUtil;

import static org.junit.Assert.assertEquals;

/**
 * <h6>Description:<h6>
 * <p></p>
 *
 * @date 2015-05-29.
 */
public class FilePathUtilTest {


    @org.junit.Test
    public void testCommandPath() throws Exception {
        assertEquals("路径计算错误", FilePathUtil.commandPath("//home/scott"), "/home/scott");
        assertEquals("路径计算错误", FilePathUtil.commandPath("c:\\home\\scott"), "c:/home/scott");
    }


    @org.junit.Test
    public void testGetParentPath() throws Exception {

    }

    @org.junit.Test
    public void testLegalFile() {
        assertEquals("判断错误", true, FilePathUtil.legalFile("c:\\1.txt"));
        assertEquals("判断错误", true, FilePathUtil.legalFile("c:/1.txt"));
        assertEquals("判断错误", true, FilePathUtil.legalFile("C:\\Program Files (x86)\\Tencent"));
        assertEquals("判断错误", false, FilePathUtil.legalFile("C:\\Program Files\" (x86)\\Tencent"));
//        assertEquals("判断错误",true,FilePathUtil.legalFile("/root/Tencent"));
//        assertEquals("判断错误",true,FilePathUtil.legalFile("../Tencent"));
    }
}