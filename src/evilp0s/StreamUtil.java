package evilp0s;

import java.io.*;

/**
 * 流相关的操作方法封装
 */
public class StreamUtil {
    /**
     * 将流另存为文件
     *
     */
    public void streamSaveAsFile(InputStream is, File outfile) {
        FileOutputStream fos = null;
        try {
            File file = outfile;
            fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) > 0){
                fos.write(buffer, 0, len);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            }
        }
    }
    /**
     * Read an input stream into a string
     */
    static public String streamToString(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
    public static byte[] stream2Byte(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = is.read(b, 0, b.length)) != -1) {
            baos.write(b, 0, len);
        }
        byte[] buffer =  baos.toByteArray();
        return buffer;
    }
    /**
     * @方法功能 InputStream 转为 byte
     */
    public static byte[] inputStream2Byte(InputStream inStream)
            throws Exception {
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        return b;
    }

    /**
     * @方法功能 byte 转为 InputStream
     * @return InputStream
     * @throws Exception
     */
    public static InputStream byte2InputStream(byte[] b) throws Exception {
        InputStream is = new ByteArrayInputStream(b);
        return is;
    }


}
