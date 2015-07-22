package evilp0s;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件名及文件路径相关的操作
 */
public class FilePathUtil {

    /**
     * 判断是否符是合法的文件路径
     *
     * @param path
     * @return
     */
    public static boolean legalFile(String path) {
        //下面的正则表达式有问题
        String regex = "[a-zA-Z]:(?:[/][^/:*?\"<>|.][^/:*?\"<>|]{0,254})+";
        //String regex ="^([a-zA-z]:)|(^\\.{0,2}/)|(^\\w*)\\w([^:?*\"><|]){0,250}";
        return RegUtil.isMatche(commandPath(path), regex);
    }

    /**
     * 返回一个通用的文件路径
     *
     * @param file
     * @return
     * @Summary windows中路径分隔符是\在linux中是/但windows也支持/方式 故全部使用/
     */
    public static String commandPath(String file) {
        return file.replaceAll("\\\\", "/").replaceAll("//", "/");
    }

    /**
     * 返回相一个目录的对于本身的相对父目录
     *
     * @param file
     * @return
     * @exmaple 当进入目录test/test/时他本身的相对于当前目录的路径为../../
     */
    public static String getParentPath(String file) {
        if (file.indexOf("/") != -1) {
            String temp = null;
            Pattern p = Pattern.compile("[/]+");
            Matcher m = p.matcher(file);
            int i = 0;
            while (m.find()) {
                temp = m.group(0);
                i += temp.length();
            }
            String parent = "";
            for (int j = 0; j < i; j++) {
                parent += "../";
            }
            return parent;
        } else {
            return "./";
        }
    }
}
