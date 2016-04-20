package com.opslab;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * <h6>Description:<h6>
 * <p>Java Class与反射相关的一些工具类</p>
 */
public class ClassUtil {


    /**
     * 获取指定类的全部属性字段
     *
     * @param className 需要获取的类名
     * @param extendsField 是否获取接口或父类中的公共属性
     * @return 属性字段数组
     */
    public static String[] getField(String className,boolean extendsField) throws ClassNotFoundException {
        Class classz = Class.forName(className);
        Field[] fields = classz.getFields();
        Set<String> set = new HashSet<>();
        if(fields != null){
            for (Field f : fields) {
                set.add(f.getName());
            }
        }
        if(extendsField){
            Field[] fieldz = classz.getDeclaredFields();
            if(fieldz != null){
                for (Field f : fieldz) {
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取类中的公共属性
     * @param className 需要获取的类名
     * @param extendsField 是否获取接口或父类中的公共属性
     * @return 属性字段数组
     */
    public static String[] getPublicField(String className,boolean extendsField)throws ClassNotFoundException {
        Class classz = Class.forName(className);
        Set<String> set = new HashSet<>();
        Field[] fields = classz.getDeclaredFields();
        if(fields != null){
            for (Field f : fields) {
                String modifier = Modifier.toString(f.getModifiers());
                if(modifier.startsWith("public")){
                    set.add(f.getName());
                }
            }
        }
        if(extendsField){
            Field[] fieldz = classz.getFields();
            if(fieldz != null){
                for (Field f : fieldz) {
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取类中定义的protected类型的属性字段
     * @param className 需要获取的类名
     * @return
     * @throws ClassNotFoundException
     */
    public static String[] getProtectedField(String className) throws ClassNotFoundException {
        Class classz = Class.forName(className);
        Set<String> set = new HashSet<>();
        Field[] fields = classz.getDeclaredFields();
        if(fields != null){
            for (Field f : fields) {
                String modifier = Modifier.toString(f.getModifiers());
                if(modifier.startsWith("protected")){
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取类中定义的private类型的属性字段
     * @param className 需要获取的类名
     * @return
     * @throws ClassNotFoundException
     */
    public static String[] getPrivateField(String className) throws ClassNotFoundException {
        Class classz = Class.forName(className);
        Set<String> set = new HashSet<>();
        Field[] fields = classz.getDeclaredFields();
        if(fields != null){
            for (Field f : fields) {
                String modifier = Modifier.toString(f.getModifiers());
                if(modifier.startsWith("private")){
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }
    /**
     * 获取对象的全部public类型方法
     *
     * @param className 需要获取的类名
     * @param extendsMethod 是否获取继承来的方法
     * @return 方法名数组
     * @throws ClassNotFoundException
     */
    public static String[] getPublicMethod(String className,boolean extendsMethod)throws ClassNotFoundException {
        Class classz = Class.forName(className);
        Method[] methods;
        if(extendsMethod){
            methods = classz.getMethods();
        }else{
            methods  = classz.getDeclaredMethods();
        }
        Set<String> set = new HashSet<>();
        if(methods != null){
            for (Method f : methods) {
                String modifier = Modifier.toString(f.getModifiers());
                if(modifier.startsWith("public")){
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }


    /**
     * 获取对象的全部protected类型方法
     *
     * @param className 需要获取的类名
     * @param extendsMethod 是否获取继承来的方法
     * @return 方法名数组
     * @throws ClassNotFoundException
     */
    public static String[] getProtectedMethod(String className,boolean extendsMethod)throws ClassNotFoundException {
        Class classz = Class.forName(className);
        Method[] methods;
        if(extendsMethod){
            methods = classz.getMethods();
        }else{
            methods  = classz.getDeclaredMethods();
        }
        Set<String> set = new HashSet<>();
        if(methods != null){
            for (Method f : methods) {
                String modifier = Modifier.toString(f.getModifiers());
                if(modifier.startsWith("protected")){
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }
    /**
     * 获取对象的全部private类型方法
     *
     * @param className 需要获取的类名
     * @param extendsMethod 是否获取继承来的方法
     * @return 方法名数组
     * @throws ClassNotFoundException
     */
    public static String[] getPrivateMethod(String className,boolean extendsMethod)throws ClassNotFoundException {
        Class classz = Class.forName(className);
        Method[] methods;
        if(extendsMethod){
            methods = classz.getMethods();
        }else{
            methods  = classz.getDeclaredMethods();
        }
        Set<String> set = new HashSet<>();
        if(methods != null){
            for (Method f : methods) {
                String modifier = Modifier.toString(f.getModifiers());
                if(modifier.startsWith("private")){
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取对象的全部方法
     *
     * @param className 需要获取的类名
     * @param extendsMethod 是否获取继承来的方法
     * @return 方法名数组
     * @throws ClassNotFoundException
     */
    public static String[] getMethod(String className,boolean extendsMethod) throws ClassNotFoundException {
        Class classz = Class.forName(className);
        Method[] methods;
        if(extendsMethod){
            methods = classz.getMethods();
        }else{
            methods  = classz.getDeclaredMethods();
        }
        Set<String> set = new HashSet<>();
        if(methods != null){
            for (Method f : methods) {
                set.add(f.getName());
            }
        }
        return set.toArray(new String[set.size()]);
    }


    /**
     * 调用对象的setter方法
     *
     * @param obj   对象
     * @param att   属性名
     * @param value 属性值
     * @param type  属性类型
     */
    public static void setter(Object obj, String att, Object value,
                              Class<?> type) throws InvocationTargetException, IllegalAccessException {
        try {
            Method met = obj.getClass().getMethod("set" + initStr(att), type);
            met.invoke(obj, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


    public static String initStr(String old) {
        return old.substring(0, 1).toUpperCase() + old.substring(1);
    }

    /**
     * 获取指定目录下所有的类名
     *
     * @param path  包名
     * @param childPackage 是否获取子包
     */
    public static List<String> getClassName(String path, boolean childPackage) {
        List<String> fileNames = new ArrayList<>();
        if(path.endsWith(".jar")){
            fileNames.addAll(getClassNameByJar(path));
        }else{
            fileNames = getClassNameByFile(path, childPackage);
        }
        return fileNames;
    }

    /**
     * 从项目文件获取某包下所有类
     *
     * @param filePath     文件路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    public static List<String> getClassNameByFile(String filePath, boolean childPackage) {
        List<String> myClassName = new ArrayList<>();
        List<File> files = FileUtil.listFile(filePath, childPackage);
        for(File file:files){
            if(file.getName().endsWith(".class")){
                String childFilePath = file.getPath();
                int  index = filePath.replaceAll("\\\\",".").length();
                childFilePath = childFilePath.replaceAll("\\\\",".").substring(index, childFilePath.length());
                myClassName.add(childFilePath);
            }
        }
        return myClassName;
    }

    /**
     * 从jar获取某包下所有类
     *
     * @param jarPath      jar文件路径
     * @return 类的完整名称
     */
    public static List<String> getClassNameByJar(String jarPath) {
        List<String> myClassName = new ArrayList<>();
        try (JarFile jarFile = new JarFile(jarPath)) {
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jarEntry = entrys.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                    myClassName.add(entryName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myClassName;
    }

    /**
     * 从所有jar中搜索该包，并获取该包下所有类
     *
     * @param urls         URL集合
     * @param packagePath  包路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    public static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
        List<String> myClassName = new ArrayList<>();
        if (urls != null) {
            for (int i = 0; i < urls.length; i++) {
                URL url = urls[i];
                String urlPath = url.getPath();
                // 不必搜索classes文件夹
                if (urlPath.endsWith("classes/")) {
                    continue;
                }
                String jarPath = urlPath + "!/" + packagePath;
                myClassName.addAll(getClassNameByJar(jarPath));
            }
        }
        return myClassName;
    }

    /**
     * 加载指定的类
     *
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public Class loadClass(String className) throws ClassNotFoundException {
        Class theClass = null;
        try {
            theClass = Class.forName(className);
        } catch (ClassNotFoundException e1) {
            try {
                theClass = Thread.currentThread().getContextClassLoader().loadClass(className);
            } catch (ClassNotFoundException e2) {
                theClass = getClass().getClassLoader().loadClass(className);
            }
        }
        return theClass;
    }

    /**
     * 获取jar包中的非*.class外的全部资源文件名字
     *
     * @param jarPath jar文件路径
     * @return
     */
    public static List<String> getResourceNameByJar(String jarPath) {
        List<String> resource = new ArrayList<>();
        try (JarFile jarFile = new JarFile(jarPath)) {
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jarEntry = entrys.nextElement();
                String entryName = jarEntry.getName();
                if (!entryName.endsWith(".class") && !entryName.endsWith("/")) {
                    resource.add(FilePathUtil.commandPath(jarPath)+"!"+entryName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resource;
    }

    /**
     * 获取jar包中的非*.class外的全部的以suffix结尾的资源文件
     *
     * @param jarPath
     * @param suffix
     * @return
     */
    public static List<String> getResourceNameByJar(String jarPath, String suffix) {
        List<String> resource = new ArrayList<>();
        try (JarFile jarFile = new JarFile(jarPath)) {
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jarEntry = entrys.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(suffix)) {
                    resource.add(FilePathUtil.commandPath(jarPath)+"!"+entryName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resource;
    }

}
