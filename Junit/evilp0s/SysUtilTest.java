package evilp0s;

public class SysUtilTest {

    @org.junit.Test
    public void testGet() throws Exception {
        System.out.println(SysUtil.HOST_IP);
        System.out.println(SysUtil.HOST_NAME);
        System.out.println(SysUtil.TotalMemorySize);
        System.out.println(SysUtil.JVM_TEMPDIR);
        System.out.println(SysUtil.OS_ARCH);
        System.out.println(SysUtil.SUN_DESKTOP);
        System.out.println(SysUtil.usedMemory());
        System.out.println(SysUtil.JVMtotalMem());
        System.out.println(SysUtil.JVMfreeMem());
        System.out.println(SysUtil.JVMmaxMem());
    }
}