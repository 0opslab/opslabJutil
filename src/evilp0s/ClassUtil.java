package evilp0s;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * <h6>Description:<h6>
 * <p>Java Class与反射相关的一些工具类</p>
 *
 * @date 2015-06-12.
 */
public class ClassUtil {


    /**
     * 获取指定类的全部属性字段
     *
     * @param className
     * @return
     */
    public static String[] getField(String className) throws ClassNotFoundException {
        Class   classz = Class.forName(className);
        Field[] fields = classz.getFields();
        Field[] fieldz = classz.getDeclaredFields();

        Set<String> set = new HashSet<String>();
        for (Field f : fields) {
            set.add(f.getName());
        }
        for (Field f : fieldz) {
            set.add(f.getName());
        }
        return set.toArray(new String[]{});
    }

    /**
     * 获取对象的全部方法
     *
     * @param className
     * @return
     */
    public static String[] getMethod(String className) throws ClassNotFoundException {
        Class       classz  = Class.forName(className);
        Method[]    methods = classz.getMethods();
        Set<String> set     = new HashSet<String>();
        for (Method f : methods) {
            set.add(f.getName());
        }
        return set.toArray(new String[]{});
    }

    /**
     * 同名属性值复制
     * 将对象obj内属性名同类型T内容有同名属性的值复制到类型T中，并返回一个类型T的对象
     *
     * @param obj
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T propertiesCopy(Object obj, Class<T> type) {
        return null;
    }

    /**
     * 同名属性值复制（忽略大小写）
     * 将对象obj内属性名同类型T内容有同名属性的值复制到类型T中，并返回一个类型T的对象
     *
     * @param obj
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T propertiesCopyIgnore(Object obj, Class<T> type) {
        return null;
    }

    /**
     * 同名属性值复制（比较时忽略字符IgnoreStr的内容）
     * 将对象obj内属性名同类型T内容有同名属性的值复制到类型T中，并返回一个类型T的对象
     *
     * @param obj
     * @param type
     * @param IgnoreStr
     * @param <T>
     * @return
     */
    public static <T> T propertiesCopyIgnoreFilter(Object obj, Class<T> type, String IgnoreStr) {
        return null;
    }

    /**
     * 调用对象的setter方法
     *
     * @param obj
     * @param att
     * @param value
     * @param type
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
        String str = old.substring(0, 1).toUpperCase() + old.substring(1);
        return str;
    }

    public static List<String> getClassName(String packageName, boolean childPackage) {
        List<String> fileNames   = null;
        ClassLoader  loader      = Thread.currentThread().getContextClassLoader();
        String       packagePath = packageName.replace(".", "/");
        URL          url         = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if (type.equals("file")) {
                fileNames = getClassNameByFile(url.getPath(), null, childPackage);
            } else if (type.equals("jar")) {
                fileNames = getClassNameByJar(url.getPath(), childPackage);
            }
        } else {
            fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, childPackage);
        }
        if (null != fileNames) {
            List<String> temp = new ArrayList<String>();
            for (String t : fileNames) {
                t = t.substring(t.indexOf(packageName), t.length());
                temp.add(t);
            }
            fileNames = temp;
        }
        return fileNames;
    }

    /**
     * 从项目文件获取某包下所有类
     *
     * @param filePath     文件路径
     * @param className    类名集合
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
        List<String> myClassName = new ArrayList<>();
        File         file        = new File(filePath);
        File[]       childFiles  = file.listFiles();
        if (ValidUtil.isValid(childFiles)) {
            for (File childFile : childFiles) {
                if (childFile.isDirectory()) {
                    if (childPackage) {
                        myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
                    }
                } else {
                    String childFilePath = childFile.getPath();
                    if (childFilePath.endsWith(".class")) {
                        childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                        childFilePath = childFilePath.replace("\\", ".");
                        myClassName.add(childFilePath);
                    }
                }
            }
        }
        return myClassName;
    }

    /**
     * 从jar获取某包下所有类
     *
     * @param jarPath      jar文件路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByJar(String jarPath, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        String[]     jarInfo     = jarPath.split("!");
        String       jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
        String       packagePath = jarInfo[1].substring(1);
        JarFile      jarFile     = null;
        try {
            jarFile = new JarFile(jarFilePath);
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jarEntry = entrys.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    if (childPackage) {
                        if (entryName.startsWith(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    } else {
                        int index = entryName.lastIndexOf("/");
                        String myPackagePath;
                        if (index != -1) {
                            myPackagePath = entryName.substring(0, index);
                        } else {
                            myPackagePath = entryName;
                        }
                        if (myPackagePath.equals(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jarFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        if (urls != null) {
            for (int i = 0; i < urls.length; i++) {
                URL url = urls[i];
                String urlPath = url.getPath();
                // 不必搜索classes文件夹
                if (urlPath.endsWith("classes/")) {
                    continue;
                }
                String jarPath = urlPath + "!/" + packagePath;
                myClassName.addAll(getClassNameByJar(jarPath, childPackage));
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

}
