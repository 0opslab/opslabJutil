package evilp0s;


import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author:Neptune
 * @Description:ProUtil 提供一些常用的属性文件相关的方法
 */
public class ProUtil {

    /**
     * @param key
     * @return
     * @从系统属性文件中获取相应的值
     */
    public static String key(String key) {
        return System.getProperty(key);
    }

    /**
     * 根据Key读取Value
     */
    public static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            pps.load(in);
            String value = pps.getProperty(key);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  读取Properties的全部信息
     */
    public static String GetAllProperties(String filePath) throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames();
        String str ="";
        while (en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            str += strKey+":"+strValue+"<>";
        }
        return str.substring(0,str.lastIndexOf("<>"));
    }

    /**
     * 写入Properties信息
     */
    public static void WriteProperties(String filePath, String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        InputStream in = new FileInputStream(filePath);
        pps.load(in);
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        pps.store(out, "Update " + pKey + " name");
    }

}
