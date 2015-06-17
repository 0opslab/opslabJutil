package evilp0s;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
/**
 * @author:Neptune
 * @Description:SysUtil 提供些获取系统信息相关的工具方法
 */
public class SysUtil {

    //主机IP
    public static String STR_IP ="";

    //主机名
    public static String STR_HOSTNAME="";

    //JVM的编码
    public static String STR_JVM_ENCODING="";
    static {
        STR_JVM_ENCODING = ProUtil.key("file.encoding");
        try {
            InetAddress addr=InetAddress.getLocalHost();
            STR_HOSTNAME = addr.getHostName();
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                if (null != netint.getHardwareAddress()) {
                    List<InterfaceAddress> list = netint.getInterfaceAddresses();
                    for (InterfaceAddress interfaceAddress : list) {
                        InetAddress ip = interfaceAddress.getAddress();
                        if (ip instanceof Inet4Address) {
                            STR_IP += interfaceAddress.getAddress().toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("获取服务器IP出错");
        }
    }
}
