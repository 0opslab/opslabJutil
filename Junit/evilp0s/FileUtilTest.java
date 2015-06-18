package evilp0s;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileUtilTest extends SupportTest{

    @Test
    public void testCountLines() throws IOException {

        String file=System.getProperty("user.dir") + "/Junit/Resource/English.txt";
        assertEquals("文件行数计算有误", 5, FileUtil.countLines(new File(file)));
    }

    @Test
    public void testHash(){
        String file =System.getProperty("user.dir") + "/Junit/Resource/ali.gif";
        assertEquals("文件Hash校验错误", "4FE6FF69F7257F2E8C36B0752B5393BF", FileUtil.hash(new File(file)).toUpperCase());
    }

    @Test
    public void testFileType() throws IOException {
        String efile = System.getProperty("user.dir") + "/Junit/Resource/ali.gif";
        String hfile = System.getProperty("user.dir") + "/Junit/Resource/tgepng";

        assertEquals("文件类型判断有错", "gif", FileUtil.FileType(new File(efile)));
        assertEquals("文件类型判断有错", "png", FileUtil.FileType(new File(hfile)));

        assertEquals("获取文件的mime类型错误", "image/gif", FileUtil.MimeType(efile));
    }

    @Test
    public void testModify(){
        String exefile = System.getProperty("user.dir")+"/Junit/Resource/cmdexe";
        Date date = FileUtil.modifyTime(new File(exefile));
        //assertEquals("获取文件修改的最后时间错误","2010-11-21 11:24:03",DateUtil.DateTime(date));
    }

    @Test
    public void testLines(){
        System.out.println("=====按List讲文件全部读入到List中======");

        PrintUtil.println("全英文文件测试");
        String efile = System.getProperty("user.dir") + "/Junit/Resource/English.txt";
        List<String> lines = FileUtil.Lines(new File(efile));
        PrintUtil.print(lines);
        PrintUtil.println("读取文件的前3行");
        lines = FileUtil.Lines(new File(efile),3);
        PrintUtil.print(lines);


        PrintUtil.println("GBK文件测试");
        String gbkfile = System.getProperty("user.dir") + "/Junit/Resource/GBK.txt";
        List<String> gbklines = FileUtil.Lines(new File(gbkfile));
        PrintUtil.print(gbklines);
        gbklines =FileUtil.Lines(new File(gbkfile),"GBK");
        PrintUtil.print(gbklines);
        PrintUtil.println("读取文件的前3行");
        lines = FileUtil.Lines(new File(gbkfile),3,"GBK");
        PrintUtil.print(lines);



        PrintUtil.println("UTF8文件测试");
        String utf8file = System.getProperty("user.dir")+"/JUnit/Resource/UTF8.txt";
        List<String> utf8lines = FileUtil.Lines(new File(utf8file));
        PrintUtil.print(utf8lines);
        utf8lines =FileUtil.Lines(new File(utf8file),"UTF-8");
        PrintUtil.print(utf8lines);
        PrintUtil.println("读取文件的前3行");
        lines = FileUtil.Lines(new File(utf8file),3,"UTF-8");
        PrintUtil.print(lines);

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
        FileUtil.copy("f:/oracle11g_winX64.rar", "c:/test/oracle.rar");
    }

    @Test
    public void testAppendLine(){
        System.out.println("向文件中追加行");
        String line1 ="E-String";
        String line2 ="中文字符串";

        File efile = new File(System.getProperty("user.dir")+"/JUnit/Resource/EnglishWrite.txt");
        File gbkfile = new File(System.getProperty("user.dir")+"/JUnit/Resource/GBKWrite.txt");
        File utf8file = new File(System.getProperty("user.dir")+"/JUnit/Resource/UTF8Write.txt");

        FileUtil.appendLine(efile,line1);
        FileUtil.appendLine(efile,line2);
        FileUtil.appendLine(efile,line1,"UTF-8");
        FileUtil.appendLine(efile,line2,"UTF-8");

        FileUtil.appendLine(gbkfile,line1);
        FileUtil.appendLine(gbkfile,line2);
        FileUtil.appendLine(gbkfile,line1,"GBK");
        FileUtil.appendLine(gbkfile,line2,"GBK");

        FileUtil.appendLine(utf8file,line1);
        FileUtil.appendLine(utf8file,line2);
        FileUtil.appendLine(utf8file,line1,"UTF-8");
        FileUtil.appendLine(utf8file,line2,"UTF-8");

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