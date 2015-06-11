package evilp0s;

import junit.framework.TestCase;

/**
 * <h6>Description:<h6>
 * <p></p>
 *
 * @date 2015-05-29.
 */
public class FilePathUtilTest extends TestCase {

    public void testCommandPath() throws Exception {
        assertEquals("路径计算错误",
                     FilePathUtil.commandPath("//home/scott"),"/home/scott");
        assertEquals("路径计算错误",
                     FilePathUtil.commandPath("c:\\home\\scott"),"c:/home/scott");


    }

    public void testGetParentPath() throws Exception {

    }
}