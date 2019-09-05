package com.opslab.helper;

import com.opslab.Opslab;
import com.opslab.functions.ObjectFilter;
import com.opslab.functions.ObjectHandler;
import com.opslab.functions.ObjectProcess;
import com.opslab.util.FileUtil;
import com.opslab.util.JacksonUtil;
import com.opslab.util.TestUtil;
import com.opslab.util.algorithmImpl.FileImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by monsoon on 18/06/2017.
 */
@Ignore
public class FileHelperTest {
    private String file = TestUtil.path + "/text/EnglishWrite.txt";

    /**
     * 逐行打印
     */
    @Test
    public void handlerWithLine() {
        FileHelper.handlerWithLine(new File(file), Opslab.UTF_8, new ObjectHandler<String>() {
            @Override
            public void handler(String s) {
                System.out.println(s);
            }
        });
    }

    /**
     * 逐行读取并获取长度大于15的行
     */
    @Test
    public void processWithLine() {
        List<String> lines = new ArrayList<String>();
        FileHelper.processWithLine(new File(file), Opslab.UTF_8, lines, new ObjectProcess<String, String>() {
            @Override
            public String process(String o) {
                if (o != null && o.length() > 15) {
                    return o;
                }
                return null;
            }
        });

        CollectionHelper.handler(lines, new ObjectHandler<String>() {
            @Override
            public void handler(String s) {
                System.out.println(s);
            }
        });

    }

    @Test
    public void fileHash() {
        File file = new File(TestUtil.path + "/text/English.txt");
        System.out.println(FileHelper.fileHash(file, "MD5"));
        System.out.println(FileHelper.fileHash(file, "SHA-1"));
        System.out.println(FileHelper.fileHash(file, "SHA-256"));
    }


    /**
     * 创建文件
     */
    @Test
    public void testCreateFile() {
        String basePath = TestUtil.path + "/temp";
        FileHelper.createFile(basePath + "/temp1/test.dat");
        FileHelper.createFile(basePath + "/temp1/temp2/temp3/test.dat");
    }


    /**
     * 测试各种方式的文件检索
     */
    @Test
    public void listFile() {
        String path = TestUtil.path + "/image";


        //常规的罗列方式
        List<File> list1 = FileHelper.listFile(new File(path));
        list1.forEach(file -> System.out.println(file.getAbsolutePath()));
        System.out.println("==================");


        //按照后缀名检索
        List<File> list2 = FileHelper.listFileSuffix(new File(path), "jpg");
        list2.forEach(file -> System.out.println(file.getAbsolutePath()));
        System.out.println("==================");


        //按照文件名检索
        List<File> list3 = FileHelper.listFileName(new File(path), "1.jpg");
        list3.forEach(file -> System.out.println(file.getAbsolutePath()));
        List<File> list4 = FileHelper.listFileNameIgnoreCase(new File(path), "1.jpg");
        list4.forEach(file -> System.out.println(file.getAbsolutePath()));

        //按照自定义方法检索
        List<File> list5 = FileHelper.listFileFilter(new File(path), new ObjectFilter() {
            @Override
            public boolean filter(Object o) {
                File file = (File) o;
                if (file.length() > 1024 * 200) {
                    return true;
                }
                return false;
            }
        });
        list5.forEach(file1 -> System.out.println(file1.getAbsolutePath()));
        System.out.println("==================");


        //测试文件名符合正则的文件罗列方式
        path = TestUtil.path + "/text";
        List<File> list = FileHelper.listFileNameReg(new File(path),
                Pattern.compile(".*\\.txt", Pattern.CASE_INSENSITIVE));
        list.forEach(file -> System.out.println(file.getAbsolutePath()));
        System.out.println("==================");


        //检索并处理文件
        FileHelper.listFileWithHandler(new File(path), new ObjectProcess<File, File>() {
            @Override
            public File process(File file) {
                try {
                    String fileName = file.getAbsolutePath();
                    String md5 = FileHelper.fileHash(file, "MD5");
                    String suffix = FileHelper.suffix(file);
                    String encoding = FileHelper.simpleEncoding(file);
                    String encoding1 = FileImpl.guestFileEncoding(file);
                    System.out.println(fileName + "\t" + md5 + "\t" + suffix + "\t" + encoding + "\t" + encoding1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        });
    }


    @Test
    public void testCopy() throws IOException {
        String basePath = System.getProperty("user.dir") + "/src/test";
        FileHelper.copy(new File(basePath + "/java"), new File(basePath + "/resource/temp/copytarget/"));
    }

    @Test
    public void testDelete() {
        File path = new File(TestUtil.path + "/temp");
        FileHelper.delete(path);
        if (!path.exists()) {
            path.mkdir();
        }
    }

    @Test
    public void readFile() {
        String content = FileHelper.readContents(new File(TestUtil.path + "/text/English.txt"));
        System.out.println("content:" + content);
    }

    @Test
    @Ignore
    public void testBase64(){
        String file = "C:\\Users\\Administrator\\Desktop\\HBASE.docx";
        String content = FileHelper.getSource(file);
        String file2 = "C:\\Users\\Administrator\\Desktop\\HBASE1.docx";
        FileHelper.sourceFile(file2 ,content);
        String fileHash = FileHelper.fileHash(new File(file), "md5");
        String file2Hash = FileHelper.fileHash(new File(file2), "md5");
        Assert.assertEquals(fileHash,file2Hash);

        String file3 = "C:\\Users\\Administrator\\Desktop\\flink_2.docx";
        String file4 = "C:\\Users\\Administrator\\Desktop\\flink_3.docx";
        List<String> strings = FileHelper.getSource(file3, 100 * 1024 );
        FileHelper.sourceFile(file4,strings);
        Assert.assertEquals(fileHash,file2Hash);
    }

}