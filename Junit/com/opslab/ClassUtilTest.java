package com.opslab;

import model.BusinessLog;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class ClassUtilTest {

    @Test
    public void testField() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        System.out.println("=============所有字段===============");
        String[] fields = ClassUtil.getField("com.opslab.RegUtil");
        for (String f : fields) {
            System.out.println(f);
        }
    }
    @Test
    public void testProtectedMetod() throws ClassNotFoundException {
        System.out.println("=============获取自定义的Protected类型的方法==========");
        String[] methods = ClassUtil.getProtectedMethod("com.opslab.bean.BeanUtil", false);
        for (String m : methods) {
            System.out.println(m);
        }
        System.out.println("=============获取自定义的和继承的Protected类型的方法==========");
        methods = ClassUtil.getProtectedMethod("com.opslab.bean.BeanUtil", true);
        for (String m : methods) {
            System.out.println(m);
        }
    }
    @Test
    public void testPublicMetod() throws ClassNotFoundException {
        System.out.println("=============获取自定义的public类型的方法==========");
        String[] methods = ClassUtil.getPublicMethod("com.opslab.bean.BeanUtil", false);
        for (String m : methods) {
            System.out.println(m);
        }
        System.out.println("=============获取自定义的和继承的public类型的方法==========");
        methods = ClassUtil.getPublicMethod("com.opslab.bean.BeanUtil", true);
        for (String m : methods) {
            System.out.println(m);
        }
    }

    @Test
    public void testMetod() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        System.out.println("============所有方法================");
        String[] methods = ClassUtil.getMethod("com.opslab.StringUtil",false);
        for (String m : methods) {
            System.out.println(m);
        }
        System.out.println("=========自定义的方法和继承来的方法===========");
        methods = ClassUtil.getMethod("com.opslab.StringUtil",true);
        for (String m : methods) {
            System.out.println(m);
        }

    }


    @Test
    public void testgetClassNameByFile(){
        String file = System.getProperty("user.dir")+"/out/production/utils";
        List<String> classNames = ClassUtil.getClassNameByFile(file, true);
        PrintUtil.print(classNames);
    }
    @Test
    public void testGetClass() {
        System.out.println("============所有类================");
        List<String> classNames = ClassUtil.getClassName("com.opslab", true);
        for (String str : classNames) {
            System.out.println(str);
        }


    }



    @Test
    public void testGetterAndSetter() throws InvocationTargetException, IllegalAccessException {

        System.out.println("============Setter和Getter================");
        BusinessLog log = new BusinessLog();
        ClassUtil.setter(log, "operationName", "setter-method-test", String.class);
        System.out.println(log);

    }

    /**
     * 测试从jar包中获取到类名字
     */
    @Test
    public void testGetClassByJar(){
        String jarPath = System.getProperty("user.dir")+"/lib/mail.jar";
        List<String> classNameList = ClassUtil.getClassNameByJar(jarPath);
        PrintUtil.print(classNameList);
    }

    /**
     * 获取jar包中的所有的资源文件
     */
    @Test
    public void testGetResourceByJar(){
        String jarPath = System.getProperty("user.dir")+"/lib/mail.jar";
        List<String> resourceNames = ClassUtil.getResourceNameByJar(jarPath);
        PrintUtil.print(resourceNames);
    }

    /**
     * 获取jar包中的自定类型的资源
     */
    @Test
    public void testGetResourceByJar1(){
        String jarPath = System.getProperty("user.dir")+"/lib/mail.jar";
        List<String> resourceNames = ClassUtil.getResourceNameByJar(jarPath,".xml");
        PrintUtil.print(resourceNames);
    }

}