package com.opslab.helper;

import com.opslab.util.CheckUtil;
import com.opslab.util.algorithmImpl.BCConvert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串常用操作方法
 */
public class StringHelper {
    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

    /**
     * 判断是否是空字符串 null和"" 都返回 true
     *
     * @param str 判断的字符串
     * @return 是否有效
     */
    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    /**
     * 判断字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str);
    }

    /**
     * 把string array or list用给定的符号symbol连接成一个字符串
     *
     * @param array  需要处理的字符串数组
     * @param symbol 链接的符号
     * @return 处理后的字符串
     */
    public static String join(String[] array, String symbol) {
        String result = "";
        if (array != null) {
            for (String temp : array) {
                if (temp != null && temp.trim().length() > 0)
                    result += (temp + symbol);
            }
            if (result.length() > 1 && CheckUtil.valid(symbol)) {
                result = result.substring(0, result.length() - symbol.length());
            }
        }
        return result;
    }


    /**
     * 根据指定的字符把源字符串分割成一个list
     *
     * @param src     处理的字符串
     * @param pattern 分割字符串
     * @return 处理后的list
     */
    public static List<String> parseString2List(String src, String pattern) {
        List<String> list = new ArrayList<>();
        if (src != null) {
            String[] tt = src.split(pattern);
            list.addAll(Arrays.asList(tt));
        }
        return list;
    }


    /**
     * 格式化一个float
     *
     * @param format 要格式化成的格式 such as #.00, #.#
     * @return 格式化后的字符串
     */
    public static String formatDouble(double f, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(f);
    }


    /**
     * 全角字符变半角字符
     *
     * @param str 需要处理的字符串
     * @return 处理后的字符串
     */
    public static String full2Half(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return BCConvert.qj2bj(str);
    }

    /**
     * 半角字符变全角字符
     *
     * @param str 需要处理的字符串
     * @return 处理后的字符串
     */
    public static String Half2Full(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return BCConvert.bj2qj(str);
    }

    /**
     * 页面中去除字符串中的空格、回车、换行符、制表符
     *
     * @param str 需要处理的字符串
     */
    public static String replaceBlank(String str) {
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }

    /**
     * 字符串转换unicode.实现native2ascii.exe类似的功能
     *
     * @param string 需要处理的字符串
     */
    public static String string2Unicode(String string) {
        StringBuilder uni = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            String temp = "\\u" + String.valueOf(Integer.toHexString(string.charAt(i)));
            uni.append(temp);
        }
        return uni.toString();
    }

    /**
     * 转字符串 实现native2ascii.exe类似的功能
     *
     * @param unicode 需要处理的字符串
     */
    public static String unicode2String(String unicode) {
        StringBuilder str = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            str.append((char) data);
        }
        return str.toString();
    }

    /**
     * 截取字符串左侧指定长度的字符串
     *
     * @param input 输入字符串
     * @param count 截取长度
     * @return 截取字符串
     */
    public static String left(String input, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(0, count);
    }

    /**
     * 截取字符串右侧指定长度的字符串
     *
     * @param input 输入字符串
     * @param count 截取长度
     * @return 截取字符串
     * Summary 其他编码的有待测试
     */
    public static String right(String input, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(input.length() - count, input.length());
    }

    /**
     * 返回俩个字符串共同的前缀
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String hasPrefix(String str1, String str2) {
        //@TODO-字符串方法实现
        return null;
    }

    /**
     * 返回俩个字符串共同的后缀
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String hasSuffix(String str1, String str2) {
        //TODO-字符串方法实现
        return null;
    }

    /**
     * 数字格式化
     *
     * @param obj    传入的小数
     * @param format 保留几位小数就穿几个0
     * @return
     */
    public static String formatNumber(BigDecimal obj, String format) {
        DecimalFormat df = new DecimalFormat(format);
        if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
            return "0" + df.format(obj);
        } else {
            return df.format(obj);
        }
    }

    /**
     * 判断字符串是否有长度
     * @param str
     * @return
     */
    public static boolean hasLength(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * 字符串替换
     * @param inString
     * @param oldPattern
     * @param newPattern
     * @return
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if (hasLength(inString) && hasLength(oldPattern) && newPattern != null) {
            int index = inString.indexOf(oldPattern);
            if (index == -1) {
                return inString;
            } else {
                int capacity = inString.length();
                if (newPattern.length() > oldPattern.length()) {
                    capacity += 16;
                }

                StringBuilder sb = new StringBuilder(capacity);
                int pos = 0;

                for (int patLen = oldPattern.length(); index >= 0; index = inString.indexOf(oldPattern, pos)) {
                    sb.append(inString.substring(pos, index));
                    sb.append(newPattern);
                    pos = index + patLen;
                }

                sb.append(inString.substring(pos));
                return sb.toString();
            }
        } else {
            return inString;
        }
    }


    /**
     * 下划线转驼峰命名
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }


}
