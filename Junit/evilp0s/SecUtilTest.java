package evilp0s;

import junit.framework.Assert;
import org.junit.Test;

import java.io.File;

public class SecUtilTest {

    @Test
    public void testMd5() throws Exception {
        String str1 = "123456";
        String str2 = SecUtil.md5(str1);
        Assert.assertEquals("计算错误","e10adc3949ba59abbe56e057f20f883e",str2);
    }

    @Test
    public void testFileMD5(){
        System.out.println(SecUtil.FileMD5(new File("c:/windows/system32/cmd.exe")));
    }
}