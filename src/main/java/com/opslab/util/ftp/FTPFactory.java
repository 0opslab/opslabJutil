package com.opslab.util.ftp;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * FTP工具类
 */
public class FTPFactory {
    private static String CONFIG_FILE;

    static {
        URL path = FTPFactory.class.getProtectionDomain().getCodeSource().getLocation();
        CONFIG_FILE = path.getPath() + "ftp.properties";
    }

    //获取一个实例
    public static FTPUtil getInstance(String Name) throws IOException {
        Properties properties = new Properties();
        try (InputStream in = new BufferedInputStream(new FileInputStream(CONFIG_FILE))) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = properties.getProperty(Name + ".host");
        if (host != null) {
            int port = Integer.parseInt(properties.getProperty(Name + ".port"));
            String username = properties.getProperty(Name + ".username");
            String password = properties.getProperty(Name + ".password");
            String remoteDir = properties.getProperty(Name + ".remoteDir");
            String localDir = properties.getProperty(Name + ".localDir");
            String Encoding = properties.getProperty(Name + ".Encoding");
            boolean passiveMode = new Boolean(properties.getProperty(Name + ".passiveMode")).booleanValue();
            FTPVo vo = new FTPVo(host, port, username, password, remoteDir, localDir, Encoding, passiveMode);
            return new FTPUtilImpl(vo);
        } else {
            throw new IOException("config error");
        }
    }
}
