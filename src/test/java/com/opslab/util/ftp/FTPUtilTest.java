package com.opslab.util.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * 用于测试FTP连接
 */
public class FTPUtilTest {

    @Test
    @Ignore
    public void test() throws IOException {

        String host = "135.224.9.67";
        int port = 21;
        String username = "ftptest";
        String password = "ftptest";
        String remoteDir = "./test";
        String localDir = "c:/download";
        String Encoding = "gbk";
        boolean passiveMode = true;
        FTPVo vo = new FTPVo(host, port, username, password, remoteDir, localDir, Encoding, passiveMode);


        FTPUtil ftp = new FTPUtilImpl(vo);

        String remoteFile = "test/22.txt";
        ftp.downLoad(remoteFile);

        //PrintUtil.print(ftp.listFile("./"));

        System.out.println("远程文件是否存在:" + ftp.isExists(remoteFile));


        System.out.println("获取当前的工作目录:" + ftp.getWorkDir());
        //ftp.downLoadDir("");

        System.out.println("创建目录:" + ftp.mkDir("test01"));
        System.out.println("创建目录:" + ftp.mkDir("test02/"));
        System.out.println("创建目录:" + ftp.mkDir("test03/test1"));
        System.out.println("创建目录:" + ftp.mkDir("test04/test1/"));

        String uploadFile = "c:/windows/system.ini";
        System.out.println("上传文件:" + ftp.putFile(uploadFile, "windows/system.ini", true));
        String deleteFile = "22.txt";
        System.out.println("删除远程文件:" + ftp.deleteFile(deleteFile));

        String deleteDir = "checkbox";
        System.out.println("删除远程目录:" + ftp.deleteDir(deleteDir));


        System.out.println(FTPConstant.REPLYCODE);
        System.out.println("上传目录:" + ftp.putDir("C:\\Python27", "python27"));
        //test FTPClient

        FTPClient client = ftp.client();

        System.out.println(client.makeDirectory("test"));
        System.out.println(client.makeDirectory("test1/"));
        System.out.println(client.makeDirectory("test/test1"));
        System.out.println(client.makeDirectory("test2/test2"));


        FTPFile[] list = client.listFiles();
        System.out.println(list);
        String[] list1 = client.listNames();
        System.out.println(list1);

        System.out.println("执行命令/" + client.sendCommand("mkdir testssss"));
        int ss = client.sendCommand("ls");

        ftp.destory();
    }
}