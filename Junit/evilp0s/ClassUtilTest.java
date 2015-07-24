package evilp0s;

import model.BusinessLog;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class ClassUtilTest {

    @Test
    public void test() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
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
        List<String> classNames = ClassUtil.getClassName("evilp0s", true);
        for (String str : classNames) {
            System.out.println(str);
        }

        System.out.println("============Setter和Getter================");
        BusinessLog log = new BusinessLog();
        ClassUtil.setter(log, "operationName", "setter-method-test", String.class);
        System.out.println(log);

    }

}