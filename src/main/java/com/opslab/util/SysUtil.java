package com.opslab.util;

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
public class SysUtil {

    /**
     * JVM的版本
     */
    public static final String JVM_VERSION     = PropertiesUtil.key("java.version");
    /**
     * JVM的编码
     */
    public static final String JVM_ENCODING    = PropertiesUtil.key("file.encoding");
    /**
     * JVM默认的临时目录
     */
    public static final String JVM_TEMPDIR     = PropertiesUtil.key("java.io.tmpdir");
    public static final String HTTP_PROXY_HOST = "http.proxyHost";
    public static final String HTTP_PROXY_PORT = "http.proxyPort";
    ;
    public static final String HTTP_PROXY_USER = "http.proxyUser";
    ;
    public static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
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
    public static String OS_ARCH           = PropertiesUtil.key("os.arch");
    /**
     * 主机类型
     */
    public static String OS_NAME           = PropertiesUtil.key("os.name");
    /**
     * 主机类型版本
     */
    public static String OS_VERSION        = PropertiesUtil.key("os.version");
    /**
     * 操作系统类型
     */
    public static String SUN_DESKTOP       = PropertiesUtil.key("sun.desktop");
    /**
     * 当前用户
     */
    public static String CURRENT_USER      = PropertiesUtil.key("user.name");
    /**
     * 当前用户的家目录
     */
    public static String CURRENT_USER_HOME = PropertiesUtil.key("user.home");
    /**
     * 当用用户的工作目录
     */
    public static String CURRENT_USER_DIR  = PropertiesUtil.key("user.dir");
    public static String FILE_SEPARATOR    = PropertiesUtil.key("file.separator");
    public static String PATH_SEPARATOR    = PropertiesUtil.key("path.separator");
    public static String LINE_SEPARATOR    = PropertiesUtil.key("line.separator");
    /**
     * 总的物理内存
     */
    public static  long                  TotalMemorySize;
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
     *
     */
    public final static long JVMtotalMem() {
        return Runtime.getRuntime().totalMemory() / kb;
    }

    /**
     * 虚拟机空闲内存量
     *
     */
    public final static long JVMfreeMem() {
        return Runtime.getRuntime().freeMemory() / kb;
    }

    /**
     * 虚拟机使用最大内存量
     *
     */
    public final static long JVMmaxMem() {
        return Runtime.getRuntime().maxMemory() / kb;
    }

    /**
     * Sets HTTP proxy settings.
     */
    public final static void setHttpProxy(String host, String port, String username, String password) {
        System.getProperties().put(HTTP_PROXY_HOST, host);
        System.getProperties().put(HTTP_PROXY_PORT, port);
        System.getProperties().put(HTTP_PROXY_USER, username);
        System.getProperties().put(HTTP_PROXY_PASSWORD, password);
    }

    /**
     * Sets HTTP proxy settings.
     */
    public final static void setHttpProxy(String host, String port) {
        System.getProperties().put(HTTP_PROXY_HOST, host);
        System.getProperties().put(HTTP_PROXY_PORT, port);
    }

}
