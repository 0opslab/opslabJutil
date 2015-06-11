package evilp0s;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 *      封装一些正则相关的操作
 */
public class RegUtil {
    /**
     * 判断字符串str是否符合正则表达式reg
     * @param str
     * @param reg
     * @return
     */
    public static boolean isMatche(String str,String reg){
        Pattern pattern = Pattern.compile(reg);
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
