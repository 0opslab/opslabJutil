package com.opslab.util;


import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class OpslabConfig {
    public static Logger logger = Logger.getLogger(OpslabConfig.class);

    /*获取CLASS_PATH*/
    public static String CLASS_PATH = "";


    public static Map<String, String> INIT_MAP = new HashMap();

    static {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = contextClassLoader.getResource("0opslab-default.properties").openStream()) {
            CLASS_PATH = new File(contextClassLoader.getResource("").toURI()).getPath();


            INIT_MAP = PropertiesUtil.properties(inputStream);

            //获取非jar包内的配置信息
            try {
                String config_file = new File(contextClassLoader.getResource("0opslab.properties").toURI()).getPath();
                Map<String, String> CONFIG_MAP = PropertiesUtil.GetAllProperties(config_file);
                INIT_MAP.putAll(CONFIG_MAP);
            } catch (Exception e) {
            }

        } catch (IOException | URISyntaxException e) {
            logger.error("init config error:" + e.getMessage());
        }
//        System.out.println(CLASS_PATH);
//        System.out.println(CollectionHelper.join(INIT_MAP, "\n", ":"));
    }


    /*主机特征码*/
    public static final String HOST_FEATURE = INIT_MAP.get("HOST_FEATURE");

    /*日期时间类型格式*/
    public static final String DATETIME_FORMAT = INIT_MAP.get("DATETIME_FORMAT");

    /*日期类型格式*/
    public static final String DATE_FORMAT = INIT_MAP.get("DATE_FORMAT");

    /*时间类型的格式*/
    public static final String TIME_FORMAT = INIT_MAP.get("TIME_FORMAT");

    /**
     * 获取配置信息
     * @param key
     * @return
     */
    public static String get(String key){
        return INIT_MAP.get(key);
    }


}
