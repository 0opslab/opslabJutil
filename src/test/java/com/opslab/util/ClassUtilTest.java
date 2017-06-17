package com.opslab.util;

import com.opslab.temp.model.BusinessLog;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;


public class ClassUtilTest {

    @Test
    public void testField() throws  InvocationTargetException, IllegalAccessException {
        System.out.println("=============所有字段===============");
        String[] fields = ClassUtil.getField("com.opslab.temp.model.BusinessLog",true);
        for (String f : fields) {
            System.out.println(f);
        }
    }

    @Test
    public void testPublicField()  {
        System.out.println("=============获取类中的全部public属性包括继承的==========");
        String[] fields = ClassUtil.getPublicField("com.opslab.temp.model.BusinessLog", true);
        for(String f:fields){
            System.out.println(f);
        }
        System.out.println("=============获取类中的自定义public属性包括继承的==========");
        String[] fieldz = ClassUtil.getPublicField("com.opslab.temp.model.BusinessLog", false);
        for(String f:fieldz){
            System.out.println(f);
        }
    }

    @Test
    public void testProtectedField()  {
        System.out.println("=============获取类中自定义的protected类型的属性==============");
        String[] field = ClassUtil.getProtectedField("com.opslab.temp.model.BusinessLog");
        for(String f:field){
            System.out.println(f);
        }
    }

    @Test
    public void testPrivateField()  {
        System.out.println("=============获取类中自定义的private类型的属性==============");
        String[] field = ClassUtil.getPrivateField("com.opslab.temp.model.BusinessLog");
        for(String f:field){
            System.out.println(f);
        }
    }
    @Test
    public void testPrivateMetod()  {
        System.out.println("=============获取自定义的private类型的方法==========");
        String[] methods = ClassUtil.getPrivateMethod("com.opslab.util.bean.BeanUtil");
        for (String m : methods) {
            System.out.println(m);
        }

    }
    @Test
    public void testProtectedMetod()  {
        System.out.println("=============获取自定义的Protected类型的方法==========");
        String[] methods = ClassUtil.getProtectedMethod("com.opslab.util.bean.BeanUtil", false);
        for (String m : methods) {
            System.out.println(m);
        }
        System.out.println("=============获取自定义的和继承的Protected类型的方法==========");
        methods = ClassUtil.getProtectedMethod("com.opslab.util.bean.BeanUtil", true);
        for (String m : methods) {
            System.out.println(m);
        }
    }
    @Test
    public void testPublicMetod()  {
        System.out.println("=============获取自定义的public类型的方法==========");
        String[] methods = ClassUtil.getPublicMethod("com.opslab.util.bean.BeanUtil", false);
        for (String m : methods) {
            System.out.println(m);
        }
        System.out.println("=============获取自定义的和继承的public类型的方法==========");
        methods = ClassUtil.getPublicMethod("com.opslab.util.bean.BeanUtil", true);
        for (String m : methods) {
            System.out.println(m);
        }
    }

    @Test
    public void testMetod() throws  InvocationTargetException, IllegalAccessException {
        System.out.println("============所有方法================");
        String[] methods = ClassUtil.getMethod("com.opslab.util.StringUtil", false);
        for (String m : methods) {
            System.out.println(m);
        }
        System.out.println("=========自定义的方法和继承来的方法===========");
        methods = ClassUtil.getMethod("com.opslab.util.StringUtil", true);
        for (String m : methods) {
            System.out.println(m);
        }

    }


    @Test
    public void testgetClassNameByFile(){
        String file = System.getProperty("user.dir")+"/target/classes";
        List<String> classNames = ClassUtil.getClassNameByFile(file, true);
        System.out.println(classNames);
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
        String jarPath = TestUtil.path +"/lib/cpdetector.jar";
        List<String> classNameList = ClassUtil.getClassNameByJar(jarPath);
        System.out.println(classNameList);
    }

    /**
     * 获取jar包中的所有的资源文件
     */
    @Test
    public void testGetResourceByJar(){
        String jarPath = TestUtil.path +"/lib/cpdetector.jar";
        List<String> resourceNames = ClassUtil.getResourceNameByJar(jarPath);
        System.out.println(resourceNames);
    }



    /**
     * 获取jar包中的自定类型的资源
     */
    @Test
    public void testGetResourceByJar1(){
        String jarPath = TestUtil.path +"/lib/cpdetector.jar";
        List<String> resourceNames = ClassUtil.getResourceNameByJar(jarPath,".xml");
        System.out.println(resourceNames);
    }

    @Test
    public void testGetSuperClass()  {
        System.out.println(ClassUtil.getSuperClass("com.opslab.temp.model.BusinessLog"));
        System.out.println(ClassUtil.getSuperClass("com.opslab.temp.model.Log"));
    }

    @Test
    public void testGetSuperClassChian()  {
        String[] superClassChian = ClassUtil.getSuperClassChian("com.opslab.temp.model.BusinessLog");
        System.out.println(Arrays.toString(superClassChian));
        System.out.println(Arrays.toString(ClassUtil.getSuperClassChian("com.opslab.temp.model.Log")));
    }

    @Test
    public void testGetInterfaces()  {
        String[] interfaces = ClassUtil.getInterfaces("java.util.List",false);
        System.out.println(Arrays.toString(interfaces));
        interfaces = ClassUtil.getInterfaces("com.opslab.temp.model.BusinessLog",false);
        System.out.println(Arrays.toString(interfaces));

        interfaces = ClassUtil.getInterfaces("com.opslab.temp.model.BusinessLog",true);
        System.out.println(Arrays.toString(interfaces));
    }

}