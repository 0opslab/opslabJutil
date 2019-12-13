package com.opslab.helper;

import com.opslab.Opslab;
import com.opslab.util.CheckUtil;
import com.opslab.util.PropertiesUtil;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * 提供些获取系统信息相关的工具方法
 */
public class SysHepler {

    /**
     * JVM的版本
     */
    public static final String JVM_VERSION = PropertiesUtil.key(Opslab.JVM_VERSION);
    /**
     * JVM的编码
     */
    public static final String JVM_ENCODING = PropertiesUtil.key(Opslab.JVM_ENCODING);
    /**
     * JVM默认的临时目录
     */
    public static final String JVM_TEMPDIR = PropertiesUtil.key(Opslab.JVM_TEMPDIR);

    /**
     * 主机IP
     */
    public static String HOST_IP;
    /**
     * 主机名
     */
    public static String HOST_NAME;

    /**
     * 主机架构
     */
    public static String OS_ARCH = PropertiesUtil.key(Opslab.SYS_OS_ARCH);
    /**
     * 主机类型
     */
    public static String OS_NAME = PropertiesUtil.key(Opslab.SYS_OS_NAME);
    /**
     * 主机类型版本
     */
    public static String OS_VERSION = PropertiesUtil.key(Opslab.SYS_OS_VERSION);
    /**
     * 操作系统类型
     */
    public static String SUN_DESKTOP = PropertiesUtil.key(Opslab.SYS_SUN_DESKTOP);


    public static String FILE_SEPARATOR = PropertiesUtil.key(Opslab.SYS_FILE_SEPARATOR);
    public static String PATH_SEPARATOR = PropertiesUtil.key(Opslab.SYS_PATH_SEPARATOR);
    public static String LINE_SEPARATOR = PropertiesUtil.key(Opslab.SYS_LINE_SEPARATOR);
    /**
     * 总的物理内存
     */
    public static long TotalMemorySize;
    private static OperatingSystemMXBean osmxb;
    private static int kb = 1024;

    static {

        try {

            InetAddress addr = InetAddress.getLocalHost();
            HOST_NAME = addr.getHostName();
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                if (null != netint.getHardwareAddress()) {
                    List<InterfaceAddress> list = netint.getInterfaceAddresses();
                    for (InterfaceAddress interfaceAddress : list) {
                        InetAddress ip = interfaceAddress.getAddress();
                        if (ip instanceof Inet4Address) {
                            HOST_IP += interfaceAddress.getAddress().toString();
                        }
                    }
                }
            }
            HOST_IP = HOST_IP.replaceAll("null", "");
        } catch (Exception e) {
            System.out.println("获取服务器IP出错");
        }

        try {
            osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            TotalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;
        } catch (Exception e) {
            System.out.println("获取系统信息失败");
            e.printStackTrace();
        }


    }


    /**
     * 已使用的物理内存
     */
    public final static long usedMemory() {
        if (CheckUtil.valid(osmxb)) {
            return (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb;
        }
        return 0;
    }

    /**
     * 获取JVM内存总量
     */
    public final static long JVMtotalMem() {
        return Runtime.getRuntime().totalMemory() / kb;
    }

    /**
     * 虚拟机空闲内存量
     */
    public final static long JVMfreeMem() {
        return Runtime.getRuntime().freeMemory() / kb;
    }

    /**
     * 虚拟机使用最大内存量
     */
    public final static long JVMmaxMem() {
        return Runtime.getRuntime().maxMemory() / kb;
    }

    /**
     * Sets HTTP proxy settings.
     */
    public final static void setHttpProxy(String host, String port, String username, String password) {
        System.getProperties().put(Opslab.HTTP_PROXY_HOST, host);
        System.getProperties().put(Opslab.HTTP_PROXY_PORT, port);
        System.getProperties().put(Opslab.HTTP_PROXY_USER, username);
        System.getProperties().put(Opslab.HTTP_PROXY_PASSWORD, password);
    }

    /**
     * Sets HTTP proxy settings.
     */
    public final static void setHttpProxy(String host, String port) {
        System.getProperties().put(Opslab.HTTP_PROXY_HOST, host);
        System.getProperties().put(Opslab.HTTP_PROXY_PORT, port);
    }

    /**
     * 判断是否符是合法的文件路径
     *
     * @param path 需要处理的文件路径
     */
    public final static boolean legalFile(String path) {
        //下面的正则表达式有问题
        String regex = "[a-zA-Z]:(?:[/][^/:*?\"<>|.][^/:*?\"<>|]{0,254})+";
        //String regex ="^([a-zA-z]:)|(^\\.{0,2}/)|(^\\w*)\\w([^:?*\"><|]){0,250}";
        return RegHepler.isMatche(commandPath(path), regex);
    }

    /**
     * 返回一个通用的文件路径
     *
     * @param file 需要处理的文件路径
     * @return Summary windows中路径分隔符是\在linux中是/但windows也支持/方式 故全部使用/
     */
    public final static String commandPath(String file) {
        return file.replaceAll("\\\\{1,}", "/").replaceAll("\\/{2,}", "/");
    }

}
