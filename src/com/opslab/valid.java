package com.opslab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

/**
 * 提供一些对象有效性校验的方法
 */
public class valid {

    /**
     * 判断字符串是否是符合指定格式的时间
     * @param date 时间字符串
     * @param format 时间格式
     * @return 是否符合
     */
    public static boolean isDate(String date,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断字符串有效性
     */
    public static boolean valid(String src) {
        return !(src == null || "".equals(src.trim()));
    }

    public static boolean valid(String... src) {
        for (String s : src) {
            if (!valid(s)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断一个对象是否为空
     */
    public static boolean valid(Object obj) {
        return !(null == obj);
    }

    public static boolean valid(Object[] objs) {
        if (objs != null && objs.length != 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合的有效性
     */
    @SuppressWarnings("rawtypes")
    public static boolean valid(Collection col) {
        return !(col == null || col.isEmpty());
    }

    public static boolean valid(Collection... cols) {
        for (Collection c : cols) {
            if (!valid(c)) {
                return false;
            }
        }
        return true;
    }


    public static boolean valid(Map map) {
        return !(map == null || map.isEmpty());
    }

    public static boolean valid(Map... maps) {
        for (Map m : maps) {
            if (!valid(m)) {
                return false;
            }
        }
        return true;
    }
}
