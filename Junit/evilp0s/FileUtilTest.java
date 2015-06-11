package evilp0s;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtilTest extends SupportTest{

    @Test
    public void testCountLines() throws IOException {
        String file ="c:/windows/WindowsUpdate.log";
        long line = FileUtil.countLines(new File(file));
        System.out.println("文件共计行数:"+line);
    }

    @Test
    public void testLines(){
        System.out.println("按List讲文件全部读入到List中");
        List<String> lines = FileUtil.Lines(new File("c:/windows/WindowsUpdate.log"));
        PrintUtil.print(lines);
    }

    @Test
    public void testMimeType() throws IOException {
        System.out.println("获取文件的mime类型");
        System.out.println(FileUtil.MimeType("D:/picture/1.jpg"));
        System.out.println(FileUtil.MimeType("c:/Python27/python.exe"));
    }

    @Test
    public void testCleanFile(){
        System.out.println("快速清空一个超大文件");
        FileUtil.cleanFile(new File("c:/windows/WindowsUpdate.log"));
    }
    @Test
    public void testCopy() throws Exception {
        System.out.println("测试文件拷贝");
        FileUtil.copy("f:/oracle11g_winX64.rar", "d:/oracle.rar");
        FileUtil.copy("f:/oracle11g_winX64.rar", "d:/oracle.rar");
        FileUtil.copy("f:/oracle11g_winX64.rar","c:/test/oracle.rar");
    }



    @Test
    public void testDeleteFile(){
        System.out.println("删除文件");
        FileUtil.deleteFile(new File("d:/oracle.rar"));
    }

    @Test
    public void testDeleteBigFile(){
        System.out.println("快速删除超大文件");
        FileUtil.deleteBigFile(new File("c:/test/oracle.rar"));
    }

    @Test
    public void testCopyDir(){
        FileUtil.copyDir(new File("c:/test"),"c:/1");
        FileUtil.copyDir("c:/python27","c:/1");
    }

    @Test
    public void testDeleteDir(){
        FileUtil.deleteDir(new File("c:/1"));
    }


    @Test
    public void testCreateFiles(){
        System.out.println("创建文件,支持多级目录");
        String file1 = "c:/test/test1.txt";
        String file2 = "c:/test/test1/test.txt";
        String file3 = "c:/test/test/";
        FileUtil.createFiles(file1);
        FileUtil.createFiles(file2);
        FileUtil.createFiles(file3);
    }

    @Test
    public void testCreatePath(){
        System.out.println("创建文件夹,支持多级目录");
        String path1 ="c:/test/test2/";
        String path2 ="c:/test/test3";
        FileUtil.createPaths(path1);
        FileUtil.createPaths(path2);
    }



    @Test
    public void testSimpleEncoding(){
        System.out.println("简单的利用文件头进行文件的编码探测!");
        System.out.println(FileUtil.SimpleEncoding("C:/Windows/system.ini"));
        System.out.println("利用cpdetector进行文件编码探测");
        System.out.println(FileUtil.cpdetector(new File("C:/Windows/system.ini")));
    }

    @Test
    public void testListFile(){
        System.out.println("罗列指定目录下的所有文件");
        List<File> files = FileUtil.listFile(new File("c:/test"));
        PrintUtil.print(files);
        List<File> files1 = FileUtil.listFile("c:/windows/");
        PrintUtil.print(files1);
    }

    @Test
    public void testListFileFilter(){
        System.out.println("罗列指定目录下的特定后缀的文件");
        List<File> files = FileUtil.listFileFilter(new File("c:/windows/"),".ini");
        PrintUtil.print(files);
    }

    @Test
    public void testSearchFile(){
        System.out.println("在指定的目录下搜索指定的文件");
        List<File> files = FileUtil.searchFile(new File("c:/windows/"), "cmd.exe");
        PrintUtil.print(files);
    }

    @Test
    public void testSearchReg(){
        System.out.println("在指定的目录下搜索符合某正则的文件");
        //匹配字母和数字组成的exe文件
        String reg ="^(?!\\D+$)(?![^a-z]+$)[a-zA-Z\\d]{1,}\\.exe$";
        List<File> files = FileUtil.searchFileReg(new File("c:/windows/"),reg);
        PrintUtil.print(files);
    }
}