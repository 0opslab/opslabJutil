package com.opslab.helper;

import com.opslab.helper.SysHepler;

import static org.junit.Assert.assertEquals;

public class SysHeplerTest {

    @org.junit.Test
    public void testGet() throws Exception {
        System.out.println(SysHepler.HOST_IP);
        System.out.println(SysHepler.HOST_NAME);
        System.out.println(SysHepler.OS_NAME);
        System.out.println(SysHepler.OS_VERSION);
        System.out.println(SysHepler.FILE_SEPARATOR);
        System.out.println(SysHepler.PATH_SEPARATOR);
        System.out.println(SysHepler.LINE_SEPARATOR);
        System.out.println(SysHepler.TotalMemorySize);
        System.out.println(SysHepler.JVM_VERSION);
        System.out.println(SysHepler.JVM_TEMPDIR);
        System.out.println(SysHepler.OS_ARCH);
        System.out.println(SysHepler.SUN_DESKTOP);
        System.out.println(SysHepler.usedMemory());
        System.out.println(SysHepler.JVMtotalMem());
        System.out.println(SysHepler.JVMfreeMem());
        System.out.println(SysHepler.JVMmaxMem());
    }


    @org.junit.Test
    public void testCommandPath() throws Exception {
        assertEquals("路径计算错误", SysHepler.commandPath("//home/scott"), "/home/scott");
        assertEquals("路径计算错误", SysHepler.commandPath("c:\\home\\scott"), "c:/home/scott");
    }


    @org.junit.Test
    public void testGetParentPath() throws Exception {

    }

    @org.junit.Test
    public void testLegalFile() {
        assertEquals("判断错误", true, SysHepler.legalFile("c:\\1.txt"));
        assertEquals("判断错误", true, SysHepler.legalFile("c:/1.txt"));
        assertEquals("判断错误", true, SysHepler.legalFile("C:\\Program Files (x86)\\Tencent"));
        assertEquals("判断错误", false, SysHepler.legalFile("C:\\Program Files\" (x86)\\Tencent"));
//        assertEquals("判断错误",true,FilePathUtil.legalFile("/root/Tencent"));
//        assertEquals("判断错误",true,FilePathUtil.legalFile("../Tencent"));
    }
}