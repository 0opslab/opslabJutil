package com.opslab.util;

/**
 * 文件名及文件路径相关的操作
 */
public final class FilePathUtil {

    /**
     * 判断是否符是合法的文件路径
     *
     * @param path 需要处理的文件路径
     */
    public final static boolean legalFile(String path) {
        //下面的正则表达式有问题
        String regex = "[a-zA-Z]:(?:[/][^/:*?\"<>|.][^/:*?\"<>|]{0,254})+";
        //String regex ="^([a-zA-z]:)|(^\\.{0,2}/)|(^\\w*)\\w([^:?*\"><|]){0,250}";
        return RegUtil.isMatche(commandPath(path), regex);
    }

    /**
     * 返回一个通用的文件路径
     *
     * @param file 需要处理的文件路径
     * @return
     * Summary windows中路径分隔符是\在linux中是/但windows也支持/方式 故全部使用/
     */
    public final static String commandPath(String file) {
        return file.replaceAll("\\\\{1,}", "/").replaceAll("\\/{2,}", "/");
    }


}
