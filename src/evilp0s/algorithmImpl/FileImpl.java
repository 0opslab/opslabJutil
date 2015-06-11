package evilp0s.algorithmImpl;


import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件相关的算法实现
 */
public class FileImpl {


    /**
     * 利用文件头特征判断文件的编码方式
     */
    public static String SimpleEncoding(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return code;
    }

    /**
     * 探测资源符的编码
     *
     * @param url
     * @return
     */
    public static String cpdetector(URL url) {
        java.nio.charset.Charset charset = null;
        try {
            charset = cpDetector.codepageDetector.detectCodepage(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (charset != null)
            return charset.name();
        else
            return null;
    }

    /**
     * 探测字符串的编码
     *
     * @param stringValue
     * @return
     */
    public static String Encoding(String stringValue) {
        java.nio.charset.Charset charset = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(stringValue.getBytes());
            charset = cpDetector.codepageDetector.detectCodepage(inputStream, 3);
            if (charset != null) {
                return charset.name();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*****************************************************
     * 以下方式利用mozilla的jchardet作为探测工具
     */

    private static boolean found = false;

    /**
     * 如果完全匹配某个字符集检测算法, 则该属性保存该字符集的名称. 否则(如二进制文件)其值就为默认值 null, 这时应当查询属性
     */
    private static String encoding = null;

    /**
     * 传入一个文件(File)对象，检查文件编码
     *
     * @param file File对象实例
     * @return 文件编码，若无，则返回null
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String guestFileEncoding(File file) throws FileNotFoundException,
            IOException {
        return geestFileEncoding(file, new nsDetector());
    }

    /**
     * 获取文件的编码
     *
     * @param file         File对象实例
     * @param languageHint 语言提示区域代码 eg：1 : Japanese; 2 : Chinese; 3 : Simplified Chinese;
     *                     4 : Traditional Chinese; 5 : Korean; 6 : Dont know (default)
     * @return 文件编码，eg：UTF-8,GBK,GB2312形式，若无，则返回null
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String guestFileEncoding(File file, int languageHint)
            throws FileNotFoundException, IOException {
        return geestFileEncoding(file, new nsDetector(languageHint));
    }

    /**
     * 获取文件的编码
     *
     * @param path 文件路径
     * @return 文件编码，eg：UTF-8,GBK,GB2312形式，若无，则返回null
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String guestFileEncoding(String path) throws FileNotFoundException,
            IOException {
        return guestFileEncoding(new File(path));
    }

    /**
     * 获取文件的编码
     *
     * @param path         文件路径
     * @param languageHint 语言提示区域代码 eg：1 : Japanese; 2 : Chinese; 3 : Simplified Chinese;
     *                     4 : Traditional Chinese; 5 : Korean; 6 : Dont know (default)
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String guestFileEncoding(String path, int languageHint)
            throws FileNotFoundException, IOException {
        return guestFileEncoding(new File(path), languageHint);
    }

    /**
     * 获取文件的编码
     *
     * @param file
     * @param det
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static String geestFileEncoding(File file, nsDetector det) throws FileNotFoundException, IOException {
        // Set an observer...
        // The Notify() will be called when a matching charset is found.
        det.Init(new nsICharsetDetectionObserver() {
            public void Notify(String charset) {
                found = true;
                encoding = charset;
            }
        });

        BufferedInputStream imp = new BufferedInputStream(new FileInputStream(file));

        byte[] buf = new byte[1024];
        int len;
        boolean done = false;
        boolean isAscii = true;

        while ((len = imp.read(buf, 0, buf.length)) != -1) {
            // Check if the stream is only ascii.
            if (isAscii){
                isAscii = det.isAscii(buf, len);
            }

            // DoIt if non-ascii and not done yet.
            if (!isAscii && !done) {
                done = det.DoIt(buf, len, false);
            }
        }
        det.DataEnd();

        if (isAscii) {
            encoding = "ASCII";
            found = true;
        }

        if (!found) {
            String prob[] = det.getProbableCharsets();
            if (prob.length > 0) {
                // 在没有发现情况下，则取第一个可能的编码
                encoding = prob[0];
            } else {
                return null;
            }
        }
        return encoding;
    }
}
