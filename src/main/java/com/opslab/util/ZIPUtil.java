package com.opslab.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 压缩文档相关的工具类
 */
public final class ZIPUtil {
    /**
     * 文档压缩
     *
     * @param file 需要压缩的文件或目录
     * @param dest 压缩后的文件名称
     * @throws IOException
     */
    public static void deCompress(File file, String dest) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest))) {
            String dir = "";
            if (file.isDirectory()) {
                dir = file.getName();
            }
            zipFile(file, zos, dir);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();
            if (files == null || files.length == 0) {
                String entryName = dir + "/";
                zos.putNextEntry(new ZipEntry(entryName));
                return;
            }
            for (File file : files) {
                String entryName = dir + "/" + file.getName();
                if (file.isDirectory()) {
                    zipFile(file, zos, entryName);
                } else {
                    ZipEntry entry = new ZipEntry(entryName);
                    zos.putNextEntry(entry);
                    try (InputStream is = new FileInputStream(file)) {
                        int len = 0;
                        while ((len = is.read()) != -1) {
                            zos.write(len);
                        }
                    } catch (IOException e) {
                        throw e;
                    }
                }
            }
        } else {
            String entryName = dir + "/" + inFile.getName();
            ZipEntry entry = new ZipEntry(entryName);
            zos.putNextEntry(entry);
            try (InputStream is = new FileInputStream(inFile)) {
                int len = 0;
                while ((len = is.read()) != -1) {
                    zos.write(len);
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

    /**
     * 文档解压
     *
     * @param source 需要解压缩的文档名称
     * @param path   需要解压缩的路径
     */
    public static void unCompress(File source, String path) throws IOException {
        ZipEntry zipEntry = null;
        FileUtil.createPaths(path);
        //实例化ZipFile，每一个zip压缩文件都可以表示为一个ZipFile
        //实例化一个Zip压缩文件的ZipInputStream对象，可以利用该类的getNextEntry()方法依次拿到每一个ZipEntry对象
        try (
                ZipFile zipFile = new ZipFile(source);
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(source))
        ) {
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String fileName = zipEntry.getName();
                String filePath = path + "/" + fileName;
                if (zipEntry.isDirectory()) {
                    File temp = new File(filePath);
                    if (!temp.exists()) {
                        temp.mkdirs();
                    }
                } else {
                    File temp = new File(filePath);
                    if (!temp.getParentFile().exists()) {
                        temp.getParentFile().mkdirs();
                    }
                    try (OutputStream os = new FileOutputStream(temp);
                         //通过ZipFile的getInputStream方法拿到具体的ZipEntry的输入流
                         InputStream is = zipFile.getInputStream(zipEntry)) {
                        int len = 0;
                        while ((len = is.read()) != -1) {
                            os.write(len);
                        }
                    } catch (IOException e) {
                        throw e;
                    }
                }


            }
        } catch (IOException e) {
            throw e;
        }
    }
}
