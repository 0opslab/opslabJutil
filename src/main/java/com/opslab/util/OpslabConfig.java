package com.opslab.util;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.File;
import java.net.URISyntaxException;

public class OpslabConfig {
    /*获取CLASS_PATH*/
    public static String CLASS_PATH = "";

    /*配置文件*/
    public static String CONFIG_FILE = "";

    static {
        try {
            CLASS_PATH = new File(OpslabConfig.class.getClassLoader().getResource("").toURI()).getPath();
            CONFIG_FILE = new File(OpslabConfig.class.getClassLoader().getResource("0opslab.properties").toURI()).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }


    /*主机特征码*/
    public static final String HOST_FEATURE = PropertiesUtil.GetValueByKey(CONFIG_FILE, "HOST_FEATURE");


    //日期时间类型格式
    public static final String DATETIME_FORMAT = PropertiesUtil.GetValueByKey(CONFIG_FILE, "DATETIME_FORMAT");

    //日期类型格式
    public static final String DATE_FORMAT = PropertiesUtil.GetValueByKey(CONFIG_FILE, "DATE_FORMAT");

    //时间类型的格式
    public static final String TIME_FORMAT = PropertiesUtil.GetValueByKey(CONFIG_FILE, "TIME_FORMAT");


}
