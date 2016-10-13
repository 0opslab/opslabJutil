package com.opslab.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * 配置相关的一些辅助类
 */
public class ConfigUtil {

    private static Logger logger = Logger.getLogger(ConfigUtil.class);

    /**
     * 获取配置文件资源
     *
     * @return
     */
    public static URL findAsResource(final String path) {
        URL url = null;

        ClassLoader contextClassLoader = ClassUtil.getContextClassLoader();
        if (contextClassLoader != null) {
            url = contextClassLoader.getResource(path);
        }
        if (url != null)
            return url;

        url = ConfigUtil.class.getClassLoader().getResource(path);
        if (url != null)
            return url;

        url = ClassLoader.getSystemClassLoader().getResource(path);

        return url;
    }

    /**
     * @param path
     * @return
     */
    public static String resourcePath(final String path) {
        URL asResource = findAsResource(path);
        return new File(asResource.getFile()).getPath();
    }


    private static InputStream getConfigStream(final String path) throws RuntimeException {
        try {
            URL url = new URL(path);
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException("Unable to open config file: " + path);
        }
    }

    /**
     * 获取资源流
     *
     * @param path
     * @return
     * @throws IOException
     */
    private static InputStream resourceStream(final String path) throws IOException {
        URL asResource = findAsResource(path);
        return asResource.openStream();
    }

    /**
     * 获取资源属性
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static Properties getConfigProperties(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(resourceStream(path));
        return properties;
    }


}
