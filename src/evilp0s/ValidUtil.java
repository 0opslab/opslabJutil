package evilp0s;

import java.util.Collection;

/**
 * @author:Neptune
 * @Description:DateUtil 提供一些对象有效性校验的方法
 */
public class ValidUtil {

    /**
     * 判断字符串有效性
     */
    public static boolean isValid(String src){
        return !(src == null || "".equals(src.trim()));
    }

    /**
     * 判断一个对象是否为空
     */
    public static boolean isValid(Object obj){
        return !(null == obj);
    }
    /**
     * 判断集合的有效性
     */
    @SuppressWarnings("rawtypes")
    public static boolean isValid(Collection col){
        return !(col == null || col.isEmpty());
    }

    /**
     * 判断数组是否有效
     */
    public static boolean isValid(Object[] arr){
        return !(arr==null || arr.length==0);
    }
}
