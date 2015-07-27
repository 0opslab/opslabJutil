package evilp0s;

import junit.framework.TestCase;

import java.io.InputStream;


public class StreamUtilTest extends TestCase {



    public void testStream2Byte() throws Exception {
        String str="中文";
        InputStream in = StreamUtil.byte2InputStream(str.getBytes(CharsetUtil.UTF_8));
        byte[]  bt =StreamUtil.stream2Byte(in);
        assertEquals(str,new String(bt));

    }

    public void testInputStream2Byte() throws Exception {
        String str="中文";
        InputStream in = StreamUtil.byte2InputStream(str.getBytes(CharsetUtil.UTF_8));
        byte[]  bt =StreamUtil.inputStream2Byte(in);
        assertEquals(str,new String(bt));
    }

    public void testByte2InputStream() throws Exception {
        String str ="中文";
        InputStream in = StreamUtil.byte2InputStream(str.getBytes(CharsetUtil.UTF_8));
        String result = StreamUtil.streamToString(in);
        assertEquals(result,str);
    }


}