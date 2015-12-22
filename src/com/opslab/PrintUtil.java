package com.opslab;

import java.util.*;

/**
 * Description:PrintUtil 提供一些常用的打印方法
 */
@SuppressWarnings("all")
public class PrintUtil {
    /**
     * @param str
     * @Function:带时间的打印
     */
    public static void print(String str) {
        System.out.println(DateUtil.DateTime() + " -> " + str);
    }

    public static void print(Object obj) {
        if (obj != null) {
            System.out.print(DateUtil.DateTime() + " -> "+obj.toString());
        }
    }

    /**
     * 遍历打印遍历List集合
     */
    public static void print(List<?> list) {
        if (ValidUtil.isValid(list)) {
            Iterator<?> iter = list.iterator();
            while (iter.hasNext()) {
                print(iter.next().toString());
            }
        }
    }

    /**
     * 遍历打印Map集合
     */
    public static void print(Map map) {
        if (ValidUtil.isValid(map)) {
            Set set = map.entrySet();
            Iterator<Map.Entry> iter = set.iterator();
            while (iter.hasNext()) {
                Map.Entry temp = iter.next();
                print(temp.getKey() + "--->" + temp.getValue());
            }
        }
    }

    /**
     * 遍历打印Enumeration
     */
    public static void print(Enumeration enums) {
        if (ValidUtil.isValid(enums)) {
            while (enums.hasMoreElements()) {
                print(enums.nextElement() + "~");
            }
        }
    }


    /**
     * 遍历打印数组
     */
    public static void print(Object[] arr) {
        if (ValidUtil.isValid(arr)) {
            for (Object a : arr) {
                print(a.toString());
            }
        }
    }

    /**
     * 打印基础类型的数组
     */
    public static void print(int[] ints){
        if(ints != null && ints.length > 0){
            StringBuffer sbuf= new StringBuffer("[");
            for(int i:ints){
                sbuf.append(i + ",");
            }
            System.out.println(StringUtil.replaceLast(sbuf.toString(),",","]"));
        }
    }

    public static void print(char[] chars){
        if(chars != null && chars.length >= 0){
            StringBuilder sbuf = new StringBuilder("[");
            for(char c:chars){
                sbuf.append(c+",");
            }
            System.out.print(StringUtil.replaceLast(sbuf.toString(), ",", "]"));
        }
    }

    public static void print(byte[] bytes){
        if(bytes != null && bytes.length >0){
            StringBuilder sbuf = new StringBuilder("[");
            for(byte b:bytes){
                sbuf.append(b+",");
            }
            System.out.print(StringUtil.replaceLast(sbuf.toString(),",","]"));
        }
    }
}
