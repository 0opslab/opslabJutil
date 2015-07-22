package evilp0s;

import java.util.Collection;
import java.util.Map;

/**
 * @author:Neptune
 * @Description:DateUtil 提供一些对象有效性校验的方法
 */
public class ValidUtil {

    /**
     * 判断字符串有效性
     */
    public static boolean isValid(String src) {
        return !(src == null || "".equals(src.trim()));
    }

    public static boolean isValid(String... src) {
        for (String s : src) {
            if (!isValid(s)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断一个对象是否为空
     */
    public static boolean isValid(Object obj) {
        return !(null == obj);
    }

    public static boolean isValid(Object[] objs) {
        if (objs != null && objs.length != 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合的有效性
     */
    @SuppressWarnings("rawtypes")
    public static boolean isValid(Collection col) {
        return !(col == null || col.isEmpty());
    }

    public static boolean isValid(Collection... cols) {
        for (Collection c : cols) {
            if (!isValid(c)) {
                return false;
            }
        }
        return true;
    }


    public static boolean isValid(Map map) {
        return !(map == null || map.isEmpty());
    }

    public static boolean isValid(Map... maps) {
        for (Map m : maps) {
            if (!isValid(m)) {
                return false;
            }
        }
        return true;
    }
}
