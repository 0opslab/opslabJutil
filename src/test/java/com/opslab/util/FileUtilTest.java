package com.opslab.util;

import com.opslab.Opslab;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
public class FileUtilTest {
    String path = TestUtil.path;

    @Test
    public void testCountLines() throws IOException {

        String file = path + "/text/EnglishWrite.txt";
        System.out.println(FileUtil.countLines(new File(file)));

    }

    @Test
    public void testHash() {
        String file = path + "/image/ali.gif";
        assertEquals("文件Hash校验错误", "4FE6FF69F7257F2E8C36B0752B5393BF",
                FileUtil.fileMD5(new File(file)).toUpperCase());
    }

    @Test
    public void testFileType() throws IOException {
        String efile = path + "/image/ali.gif";
        String hfile = path + "/image/tge.png";

        assertEquals("文件类型判断有错", "gif", FileUtil.fileType(new File(efile)));
        assertEquals("文件类型判断有错", "png", FileUtil.fileType(new File(hfile)));

        assertEquals("获取文件的mime类型错误", "image/gif", FileUtil.mimeType(efile));
    }

    @Test
    public void testModify() {
        String exefile = path + "cmdexe";
        Date date = FileUtil.modifyTime(new File(exefile));
        //assertEquals("获取文件修改的最后时间错误","2010-11-21 11:24:03",DateHelper.DateTime(date));
    }

    @Test
    public void testLines() {
        System.out.println("=====按List讲文件全部读入到List中======");

        System.out.println("全英文文件测试");
        String efile = path + "/text/English.txt";
        List<String> lines = FileUtil.lines(new File(efile));
        System.out.println(lines);
        System.out.println("读取文件的前3行");
        lines = FileUtil.lines(new File(efile), 3);
        System.out.println(lines);


        System.out.println("GBK文件测试");
        String gbkfile = path + "/text/GBK.txt";
        List<String> gbklines = FileUtil.lines(new File(gbkfile));
        System.out.println(gbklines);
        gbklines = FileUtil.lines(new File(gbkfile), "GBK");
        System.out.println(gbklines);
        System.out.println("读取文件的前3行");
        lines = FileUtil.lines(new File(gbkfile), 3, "GBK");
        System.out.println(lines);


        System.out.println("UTF8文件测试");
        String utf8file = path + "/text/UTF8.txt";
        List<String> utf8lines = FileUtil.lines(new File(utf8file));
        System.out.println(utf8lines);
        utf8lines = FileUtil.lines(new File(utf8file), Opslab.UTF_8);
        System.out.println(utf8lines);
        System.out.println("读取文件的前3行");
        lines = FileUtil.lines(new File(utf8file), 3, Opslab.UTF_8);
        System.out.println(lines);

    }


    @Test
    public void testCleanFile() {
        System.out.println("快速清空一个超大文件");
        String clearFile = path + "/text/GBKTOUTF8.txt";
        FileUtil.cleanFile(new File(clearFile));
    }

    @Test
    public void testCopy() throws Exception {
        System.out.println("测试文件拷贝");
        String copy = TestUtil.path + "/text/GBKTOUTF8.txt";
        String dest = TestUtil.path + "temp/GBKTOUTF8.txt";
        FileUtil.copy(copy, dest);
    }

    @Test
    public void testAppendLine() {
        System.out.println("向文件中追加行");


        String line1 = "E-String";
        String line2 = "中文字符串";

        File efile = new File(path + "/text/EnglishWrite.txt");
        File gbkfile = new File(path + "/text/GBKWrite.txt");
        File utf8file = new File(path + "/text/UTF8Write.txt");
        FileUtil.appendLine(efile, line1);
        FileUtil.appendLine(efile, line2);
        FileUtil.appendLine(efile, line1, Opslab.UTF_8);
        FileUtil.appendLine(efile, line2, Opslab.UTF_8);

        FileUtil.appendLine(gbkfile, line1);
        FileUtil.appendLine(gbkfile, line2);
        FileUtil.appendLine(gbkfile, line1, "GBK");
        FileUtil.appendLine(gbkfile, line2, "GBK");

        FileUtil.appendLine(utf8file, line1);
        FileUtil.appendLine(utf8file, line2);
        FileUtil.appendLine(utf8file, line1, Opslab.UTF_8);
        FileUtil.appendLine(utf8file, line2, Opslab.UTF_8);

    }


    @Test
    public void testDeleteFile() {
        System.out.println("删除文件");
        String delete = TestUtil.path + "temp/delete.txt";
        FileUtil.deleteFile(new File(delete));
    }

    @Test
    public void testDeleteBigFile() {
        System.out.println("快速删除超大文件");
        String delete = TestUtil.path + "temp/bigfile.txt";
        FileUtil.deleteBigFile(new File(delete));
    }

    @Test
    public void testCopyDir() {
        FileUtil.copyDir(new File(TestUtil.path + "/image/"), path + "/temp/copy");
    }


    @Test
    public void testCreateFiles() {
        System.out.println("创建文件,支持多级目录");
        String path = TestUtil.path + "temp/";
        String file1 = path + "/test/test1.txt";
        String file2 = path + "/test/test1/test.txt";
        String file3 = path + "/test/test/";
        FileUtil.createFiles(file1);
        FileUtil.createFiles(file2);
        FileUtil.createFiles(file3);
    }

    @Test
    public void testDeleteDir() {
        String path = TestUtil.path + "/temp/";
        FileUtil.deleteDir(new File(path + "/test/"));
    }

    @Test
    public void testCreatePath() {
        System.out.println("创建文件夹,支持多级目录");
        String path = TestUtil.path + "temp/";
        String path1 = path + "/test/test2/";
        String path2 = path + "/test/test3";
        FileUtil.createPaths(path1);
        FileUtil.createPaths(path2);
    }


    @Test
    public void testListFile() {
        System.out.println("罗列指定目录下的所有文件");
        String path = TestUtil.path + "temp/";
        List<File> files = FileUtil.listFile(new File(path));
        System.out.println(files);
        List<File> files1 = FileUtil.listFile(path);
        System.out.println(files1);
    }

    @Test
    public void testListFile1() {
        String path = TestUtil.path + "temp/";
        List<File> files = FileUtil.listFile(path, false);
        System.out.println(files);
        System.out.println("=============");
        files = FileUtil.listFile(path, true);
        System.out.println(files);
    }

    @Test
    public void testCopyDirs() {
        System.out.println("复制文件夹");
        String path = TestUtil.path + "/temp";
        FileUtil.copyDir(path + "/test/", path + "/testcopy/");
    }

    @Test
    public void testListFileFilter() {
        System.out.println("罗列指定目录下的特定后缀的文件");
        String path = TestUtil.path;
        List<File> files = FileUtil.listFileFilter(new File(path), ".txt");
        System.out.println(files);
    }

    @Test
    public void testSearchFile() {
        System.out.println("在指定的目录下搜索指定的文件");
        List<File> files = FileUtil.searchFile(new File(TestUtil.path), "GBK.txt");
        System.out.println(files);
    }

    @Test
    public void testSearchReg() {
        System.out.println("在指定的目录下搜索符合某正则的文件");
        //匹配字母和数字组成的exe文件
        String reg = "\\w{1,}\\.png$";
        List<File> files = FileUtil.searchFileReg(new File(TestUtil.path), reg);
        System.out.println(files);
    }

    @Test
    public void testWriteFile() {
        String path = TestUtil.path + "temp/";
        String etemp = "Two roads diverged in a yellow wood,\n" +
                "And sorry I could not travel both\n" +
                "And be one traveler, long I stood\n" +
                "And looked down one as far as I could\n" +
                "To where it bent in the undergrowth;\n" +
                "Then took the other, as just as fair,\n" +
                "And having perhaps the better claim,\n" +
                "Because it was grassy and wanted wear;\n" +
                "Though as for that the passing there\n" +
                "Had worn them really about the same,\n" +
                "And both that morning equally layn" +
                "In leaves no step had trodden black.\n" +
                "Oh, I kept the first for another day!\n" +
                "Yet knowing how way leads on to way,\n" +
                "I doubted if I should ever come back.\n" +
                "I shall be telling this with a sigh\n" +
                "Somewhere ages and ages hence:\n" +
                "Two roads diverged in a wood,and\n" +
                "I took the one less traveled by,\n" +
                "And that has made al lthe difference.";

        String ctemp = "黄色的树林里分出两条路\n" +
                "可惜我不能同时去涉足\n" +
                "我在那路口久久伫立\n" +
                "我向着一条路极目望去\n" +
                "直到它消失在丛林深处\n" +
                "但我却选择了另外一条路\n" +
                "它荒草萋萋，十分幽寂 　　\n" +
                "显得更诱人，更美丽　\n" +
                "虽然在这两条小路上\n" +
                "都很少留下旅人的足迹\n" +
                "虽然那天清晨落叶满地\n" +
                "两条路都未经脚印污染\n" +
                "呵，留下一条路等改日再见\n" +
                "但我知道路径延绵无尽头\n" +
                "恐怕我难以再回返\n" +
                "也许多少年后在某一个地方\n" +
                "我将轻声叹息把往事回顾\n" +
                " 一片森林里分出两条路\n" +
                "而我却选择了人迹更少的一条\n" +
                "从此决定了我一生的道路";
        FileUtil.write(new File(path + "efile1.TXT"), etemp);
        FileUtil.write(new File(path + "efile2.TXT"), etemp);
        FileUtil.writeAppend(new File(path + "efile2.TXT"), etemp);
        FileUtil.write(new File(path + "cfile1.TXT"), ctemp);
        FileUtil.write(new File(path + "cfile2.TXT"), ctemp);
        FileUtil.writeAppend(new File(path + "cfile2.TXT"), ctemp);

        FileUtil.write(new File(path + "efile1_UTF8.TXT"), etemp, Opslab.UTF_8);
        FileUtil.write(new File(path + "efile2_UTF8.TXT"), etemp, Opslab.UTF_8);
        FileUtil.writeAppend(new File(path + "efile2_UTF8.TXT"), etemp, Opslab.UTF_8);
        FileUtil.write(new File(path + "cfile1_UTF8.TXT"), ctemp, Opslab.UTF_8);
        FileUtil.write(new File(path + "cfile2_UTF8.TXT"), ctemp, Opslab.UTF_8);
        FileUtil.writeAppend(new File(path + "cfile2_UTF8.TXT"), ctemp, Opslab.UTF_8);
    }
}