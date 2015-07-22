package FTP;

import evilp0s.FTP.FTPConstant;
import evilp0s.FTP.FTPFactory;
import evilp0s.FTP.FTPUtil;
import evilp0s.PrintUtil;

import java.io.IOException;

/**
 * FTP工具类测试
 */
public class FTPtest {
    public static void main(String[] args) throws IOException {
        FTPUtil ftp = FTPFactory.getInstance("FTPTest");
        /*
        String remoteFile = "test/22.txt";
        ftp.downLoad(remoteFile);

        //PrintUtil.print(ftp.listFile("./"));

        System.out.println("远程文件是否存在:" + ftp.isExists(remoteFile));


        System.out.println("获取当前的工作目录:" + ftp.getWorkDir());
        //ftp.downLoadDir("");

        System.out.println("创建目录:"+ftp.mkDir("test01"));
        System.out.println("创建目录:"+ftp.mkDir("test02/"));
        System.out.println("创建目录:"+ftp.mkDir("test03/test1"));
        System.out.println("创建目录:"+ftp.mkDir("test04/test1/"));

        String uploadFile ="c:/windows/system.ini";
        System.out.println("上传文件:"+ftp.putFile(uploadFile,"windows/system.ini",true));
         String deleteFile ="22.txt";
        System.out.println("删除远程文件:"+ftp.deleteFile(deleteFile));

        String deleteDir ="checkbox";
        System.out.println("删除远程目录:"+ftp.deleteDir(deleteDir));


*/
        PrintUtil.print(FTPConstant.REPLYCODE);
        System.out.println("上传目录:" + ftp.putDir("C:\\Python27", "python27"));
        //test FTPClient
   /*
        FTPClient client = ftp.client();

        System.out.println(client.makeDirectory("test"));
        System.out.println(client.makeDirectory("test1/"));
        System.out.println(client.makeDirectory("test/test1"));
        System.out.println(client.makeDirectory("test2/test2"));


        FTPFile[] list  = client.listFiles();
        System.out.println(list);
        String[] list1 = client.listNames();
        System.out.println(list1);

        System.out.println("执行命令/" + client.sendCommand("mkdir testssss"));
        int ss = client.sendCommand("ls");
        */
        ftp.destory();
    }

}
