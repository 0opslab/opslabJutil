package evilp0s;

import model.BusinessLog;
import org.junit.Test;

import java.util.List;

/**
 * <h6>Description:<h6>
 * <p></p>
 *
 * @date 2015-06-12.
 */
public class ClassUtilTest  {

    @Test
    public  void test() throws ClassNotFoundException {
        System.out.println("=============所有字段===============");
        String[] fields = ClassUtil.getField("evilp0s.StringUtil");
        for (String f : fields) {
            System.out.println(f);
        }

        System.out.println("============所有方法================");
        String[] methods = ClassUtil.getMethod("evilp0s.StringUtil");
        for (String m : methods) {
            System.out.println(m);
        }

        System.out.println("============所有类================");
        List<String> classNames = ClassUtil.getClassName(
                "evilp0s",
                true);
        for (String str : classNames) {
            System.out.println(str);
        }

        System.out.println("============Setter和Getter================");
        BusinessLog log = new BusinessLog();
        ClassUtil.setter(
                log,
                "operationName",
                "setter-method-test",
                String.class);
        System.out.println(log);

    }

}