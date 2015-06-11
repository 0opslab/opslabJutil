package evilp0s;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 压缩文档相关的工具类
 */
public class ZIPUtil {
    /**
     * 文档压缩
     *
     * @param file 需要压缩的文件或目录
     * @param dest 压缩后的文件名称
     * @throws Exception
     */
    public static void deCompress(File file, String dest) throws Exception {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));
        zipFile(file, zos, "");
        zos.close();
    }

    public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();
            for (File file : files){
                String name = inFile.getName();
                if(!"".equals(dir)){
                    name=dir +"/"+name;
                }
                zipFile(file, zos,name);
            }
        } else {
            String entryName = null;
            if (!"".equals(dir)) {
                entryName = dir + "/" + inFile.getName();
            }else {
                entryName = inFile.getName();
            }
            ZipEntry entry = new ZipEntry(entryName);
            zos.putNextEntry(entry);
            InputStream is = new FileInputStream(inFile);
            int len = 0;
            while ((len = is.read()) != -1) {
                zos.write(len);
            }
            is.close();
        }
    }

    /**
     * 文档解压
     *
     * @param source 需要解压缩的文档名称
     * @param path   需要解压缩的路径
     */
    public static void unCompress(File source, String path) throws IOException {
        ZipFile zipFile = new ZipFile(source);//实例化ZipFile，每一个zip压缩文件都可以表示为一个ZipFile
        //实例化一个Zip压缩文件的ZipInputStream对象，可以利用该类的getNextEntry()方法依次拿到每一个ZipEntry对象
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(source));
        ZipEntry zipEntry = null;
        FileUtil.createPaths(path);
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            String fileName = zipEntry.getName();
            File temp = new File(path+"/"+fileName);
            if (! temp.getParentFile().exists()) {
                temp.getParentFile().mkdirs();
            }
            OutputStream os = new FileOutputStream(temp);
            //通过ZipFile的getInputStream方法拿到具体的ZipEntry的输入流
            InputStream is = zipFile.getInputStream(zipEntry);
            int len = 0;
            while ((len = is.read()) != -1)
            os.write(len);
            os.close();
            is.close();
        }
        zipInputStream.close();

    }
}
