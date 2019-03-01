package com.opslab.helper;

import com.opslab.functions.ObjectFilter;
import com.opslab.functions.ObjectHandler;
import com.opslab.functions.ObjectProcess;
import com.opslab.util.AssertUtil;
import com.opslab.util.CheckUtil;
import com.opslab.util.SysUtil;
import com.opslab.util.ZIPUtil;
import com.opslab.util.algorithmImpl.FileImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 一些操作文件的便捷方法
 */
public final class FileHelper {
    private static Logger logger = LoggerFactory.getLogger(FileHelper.class);

    /**
     * 逐行处理
     *
     * @param file     handler file
     * @param encoding file encoding
     * @param handler  handler
     */
    public static void handlerWithLine(File file, String encoding, ObjectHandler<String> handler) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                handler.handler(line);
            }
        } catch (IOException e) {
            logger.error("handler error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 逐行处理
     *
     * @param file     需要处理的文件
     * @param encoding 文件编码
     * @param result   接受处理结果的集合
     * @param process  处理过程实现
     * @param <E>
     */
    public static <E> void processWithLine(File file, String encoding, Collection<E> result, ObjectProcess<String, E> process) {
        if (result == null) {
            logger.info("receive collection is null");
            return;
        }
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                E tmpLine = process.process(line);
                if (tmpLine != null) {
                    result.add(tmpLine);
                }
            }
        } catch (IOException e) {
            logger.error("process error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////
    // 写文件的方法(如果写入的数据较多建议使用通道的方式)

    /**
     * 获取文件的行数
     *
     * @param file 统计的文件
     * @return 文件行数
     */
    public static int lineCounts(File file) {
        try (LineNumberReader rf = new LineNumberReader(new FileReader(file))) {
            long fileLength = file.length();
            rf.skip(fileLength);
            return rf.getLineNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 以列表的方式获取文件的所有行
     *
     * @param file 需要出来的文件
     * @return 包含所有行的list
     */
    public static List<String> readLines(File file) {
        List<String> list = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 以列表的方式获取文件的所有行
     *
     * @param file     需要处理的文件
     * @param encoding 指定读取文件的编码
     * @return 包含所有行的list
     */
    public static List<String> readLines(File file, String encoding) {
        List<String> list = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 读取文件内容
     *
     * @param file
     * @return
     */
    public static String readContents(File file) {
        try (FileInputStream in = new FileInputStream(file)) {
            Long filelength = file.length();
            byte[] filecontent = new byte[filelength.intValue()];
            ;
            in.read(filecontent);
            return new String(filecontent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建文件支持多级目录
     *
     * @param file 需要创建的文件
     * @return 是否成功, 如果存在则返回成功
     */
    public final static boolean createFiles(File file) {
        if (file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            if (!file.exists()) {
                return file.mkdirs();
            }
        } else {
            File dir = file.getParentFile();
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                    try {
                        return file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    return file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 创建文件支持多级目录
     *
     * @param file 需要创建的文件
     * @return 是否成功, 如果存在则返回成功
     * @para isReNew 存在的时候是否重新创建
     */
    public final static boolean createFiles(File file, boolean isReNew) {
        if (file.exists()) {
            if (isReNew) {
                if (file.delete()) {
                    try {
                        return file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
        if (file.isDirectory()) {
            if (!file.exists()) {
                return file.mkdirs();
            }
        } else {
            File dir = file.getParentFile();
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                    try {
                        return file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    return file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    ///////////////////////////////////////////////////////////////////////
    // 写文件的方法(如果写入的数据较多建议使用通道的方式)

    /**
     * 写文件
     *
     * @param file 需要处理的函数
     * @param str  添加的子字符串
     * @return 是否成功
     */
    public static boolean write(File file, String str) {
        AssertUtil.notNull(file, "file is null");
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            randomFile.writeBytes(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写文件
     *
     * @param file     需要处理的文件
     * @param str      添加的字符串
     * @param encoding 指定写入的编码
     * @return 是否成功
     */
    public static boolean write(File file, String str, String encoding) {
        AssertUtil.notNull(file, "file is null");
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            randomFile.write((str).getBytes(encoding));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写文件
     *
     * @param file 需要处理的函数
     * @param str  添加的子字符串
     * @return 是否成功
     */
    public static boolean addWrite(File file, String str) {
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写文件
     *
     * @param file     需要处理的文件
     * @param str      添加的字符串
     * @param encoding 指定写入的编码
     * @return 是否成功
     */
    public static boolean addWrite(File file, String str, String encoding) {
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write((str).getBytes(encoding));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    ///////////////////////////////////////////////////////////////////////
    // 复制文件的方法

    /**
     * 复制文件
     *
     * @param resource 源文件
     * @param target   目标文件
     * @return 是否成功
     */
    public static boolean copy(File resource, File target) throws IOException {
        if (resource == null) {
            logger.error("copy  resource is null");
            return false;
        }
        if (resource.isFile()) {
            return copyFile(resource, target);
        }
        File[] files = resource.listFiles();
        if (files == null || files.length == 0) {
            return target.mkdirs();
        }
        target.mkdirs();
        for (File file : files) {
            String targetFilePath = file.getAbsolutePath().substring(resource.getAbsolutePath().length());
            File targetFile = new File(target + "/" + targetFilePath);
            if (file.isDirectory()) {
                targetFile.mkdirs();
                copy(file, targetFile);
            } else {
                copyFile(file, targetFile);
            }
        }
        return true;

    }

    /**
     * 复制文件
     * 通过该方式复制文件文件越大速度越是明显
     *
     * @param file       需要处理的文件
     * @param targetFile 目标文件
     * @return 是否成功
     */
    public static boolean copyFile(File file, File targetFile) throws IOException {
        logger.debug("copy file resource:{} ,target:{}", file.getAbsolutePath(), targetFile.getAbsolutePath());
        int BUFFER_SIZE = 1024 * 1024;
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        targetFile.createNewFile();
        try (
                FileInputStream fin = new FileInputStream(file);
                FileOutputStream fout = new FileOutputStream(targetFile)
        ) {
            FileChannel in = fin.getChannel();
            FileChannel out = fout.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (in.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
            return true;
        } catch (IOException e) {
            throw e;
        }
    }


    ///////////////////////////////////////////////////////////////////////
    // 删除文件的方法

    /**
     * 快速清空一个超大的文件
     *
     * @param file 需要处理的文件
     * @return 是否成功
     */
    public static boolean cleanFile(File file) {
        try (
                FileWriter fw = new FileWriter(file)
        ) {
            fw.write("");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除一个目录
     *
     * @param file 需要处理的文件
     * @return 是否成功
     */
    public static boolean delete(File file) {
        if (file == null)
            return false;

        if (file.isFile())
            return file.delete();

        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            return file.delete();
        }
        for (File ff : files) {
            if (file.isDirectory()) {
                delete(ff);
            } else {
                if (ff.length() > 1024 * 1024 * 1024) {
                    cleanFile(ff);
                }
                ff.delete();
            }
        }
        return file.delete();
    }


    ///////////////////////////////////////////////////////////////////////
    // 检索文件的方法

    /**
     * 罗列指定路径下的全部文件
     *
     * @param path 需要处理的文件
     * @return 返回文件列表
     */
    public static List<File> listFile(File path) {
        List<File> list = new ArrayList<>();
        if (path != null && path.exists() && path.isDirectory()) {
            File[] files = path.listFiles();
            if (files == null || files.length == 0) {
                return list;
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFile(file));
                } else {
                    list.add(file);
                }
            }
        }
        return list;
    }


    /**
     * 获取指定目录下的特点文件,通过后缀名过滤
     *
     * @param dirPath  需要处理的文件
     * @param postfixs 文件后缀
     * @return 返回文件列表
     */
    public static List<File> listFileSuffix(File dirPath, String postfixs) {
        List<File> list = new ArrayList<>();
        if (dirPath != null && dirPath.exists() && dirPath.isDirectory()) {
            File[] files = dirPath.listFiles();
            if (files == null || files.length == 0) {
                return list;
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileSuffix(file, postfixs));
                } else {
                    String fileName = file.getName().toLowerCase();
                    if (fileName.endsWith(postfixs.toLowerCase())) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 在指定的目录下按照文件名查找文件
     *
     * @param dirPath  搜索的目录
     * @param fileName 搜索的文件名
     * @return 返回文件列表
     */
    public static List<File> listFileName(File dirPath, String fileName) {
        List<File> list = new ArrayList<>();
        if (dirPath != null && dirPath.exists() && dirPath.isDirectory()) {
            File[] files = dirPath.listFiles();
            if (files == null || files.length == 0) {
                return list;
            }

            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileName(file, fileName));
                } else {
                    String Name = file.getName();
                    if (Name.equals(fileName)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 在指定的目录下按照文件名查找文件忽略文件带下
     *
     * @param dirPath  搜索的目录
     * @param fileName 搜索的文件名
     * @return 返回文件列表
     */
    public static List<File> listFileNameIgnoreCase(File dirPath, String fileName) {
        List<File> list = new ArrayList<>();
        if (dirPath != null && dirPath.exists() && dirPath.isDirectory()) {
            File[] files = dirPath.listFiles();
            if (files == null || files.length == 0) {
                return list;
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileName(file, fileName));
                } else {
                    String Name = file.getName();
                    if (Name.equalsIgnoreCase(fileName)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 罗列指定路径下的全部文件包括文件夹
     *
     * @param path   需要处理的文件
     * @param filter 处理文件的filter
     * @return 返回文件列表
     */
    public static List<File> listFileFilter(File path, ObjectFilter filter) {
        List<File> list = new ArrayList<>();
        if (path != null && path.exists() && path.isDirectory()) {
            File[] files = path.listFiles();
            if (files == null || files.length == 0) {
                return list;
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileFilter(file, filter));
                } else {
                    if (filter.filter(file)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }


    /**
     * 罗列指定目录下的文件名符合正则表达式的文件
     *
     * @param dirPath 搜索的目录
     * @param pattern 正则表达式
     * @return 返回文件列表
     */
    public static List<File> listFileNameReg(File dirPath, Pattern pattern) {
        List<File> list = new ArrayList<>();
        if (dirPath != null && dirPath.exists() && dirPath.isDirectory()) {
            File[] files = dirPath.listFiles();
            if (files == null || files.length == 0) {
                return list;
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileNameReg(file, pattern));
                } else {
                    if (pattern.matcher(file.getName()).matches()) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 处理并检索文件
     *
     * @param path
     * @param handler
     * @return
     */
    public static List<File> listFileWithHandler(File path, ObjectProcess<File, File> handler) {
        List<File> list = new ArrayList<>();
        if (path != null && path.exists() && path.isDirectory()) {
            File[] files = path.listFiles();
            if (files == null || files.length == 0) {
                return list;
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileWithHandler(file, handler));
                } else {
                    File ff = handler.process(file);
                    if (ff != null) {
                        list.add(ff);
                    }
                }
            }
        }
        return list;
    }


    ///////////////////////////////////////////////////////////////////////
    //其他杂项方法

    /**
     * 创建文件支持多级目录
     *
     * @param path 需要创建的文件
     * @return 是否成功
     */
    public static boolean createFile(String path) {
        if (path != null && path.length() > 0) {
            try {
                File file = new File(path);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                return true;
            } catch (Exception e) {
                logger.error("create file exception :" + path + ",Exception" + e.getMessage());
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 获取文件后缀名
     *
     * @param file file
     * @return file's suffix
     */

    public static String suffix(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.indexOf(".") + 1);
    }

    /**
     * 获取文件的hash
     *
     * @param file     file
     * @param HashTyle MD5,SHA-1,SHA-256
     * @return
     */
    public static String fileHash(File file, String HashTyle) {
        try (InputStream fis = new FileInputStream(file)) {
            MessageDigest md = MessageDigest.getInstance(HashTyle);
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            byte[] md5Bytes = md.digest();
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            logger.error("get filehash error" + e.getMessage());
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 利用字节特征探测文件编码
     *
     * @param file 需要处理的文件
     * @return UTF-8 Unicode UTF-16BE GBK or null
     */
    public static String simpleEncoding(File file) {
        try {
            return FileImpl.guestFileEncoding(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 文件压缩支持文件和文件夹
     *
     * @throws Exception
     */
    public static boolean zipDeCompress(File file, String zipFile) {
        try {
            ZIPUtil.deCompress(file, zipFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 文件压缩
     *
     * @param zipFile
     * @param path
     * @return
     */
    public static boolean zipUnCompress(File zipFile, String path) {
        try {
            ZIPUtil.unCompress(zipFile, path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
