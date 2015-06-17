package evilp0s;

import evilp0s.algorithmImpl.FileImpl;
import evilp0s.algorithmImpl.FileTypeImpl;

import java.io.*;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 封装了些文件相关的操作
 */
public class FileUtil {
    private static Integer BUFFER_SIZE = 1024 * 1024 * 10;

    /**
     * 获取文件的行数
     */
    public static int countLines(File file) throws IOException {
        InputStream is = null;
        int count = 0;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            byte[] c = new byte[BUFFER_SIZE];
            int readChars = 0;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
            }
        } finally {
            is.close();
        }
        return count;
    }

    /**
     * 以列表的方式获取文件的所有行
     */
    public static List<String> Lines(File file) {
        BufferedReader reader = null;
        List<String> list = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 以列表的方式获取文件的所有行
     * @param file
     * @param encoding 指定读取文件的编码
     * @return
     */
    public static List<String> Lines(File file,String encoding){
        BufferedReader reader = null;
        List<String> list = new ArrayList<String>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 以列表的方式获取文件的指定的行数数据
     */
    public static List<String> Lines(File file, int lines) {
        BufferedReader reader = null;
        List<String> list = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(line);
                if (list.size() == lines) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     *  以列表的方式获取文件的指定的行数数据
     * @param file
     * @param lines
     * @param encoding 指定读取文件的编码
     * @return
     */
    public static List<String> Lines(File file, int lines,String encoding) {
        BufferedReader reader = null;
        List<String> list = new ArrayList<String>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(line);
                if (list.size() == lines) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 在文件末尾追加一行
     */
    public static boolean appendLine(File file, String str) {
        RandomAccessFile randomFile = null;
        String lineSeparator = System.getProperty("line.separator", "\n");
        try {
            randomFile = new RandomAccessFile(file, "rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(lineSeparator+str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                randomFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 在文件末尾追加一行
     * @param file
     * @param str
     * @param encoding 指定写入的编码
     * @return
     */
    public static boolean appendLine(File file, String str,String encoding) {
        RandomAccessFile randomFile = null;
        String lineSeparator = System.getProperty("line.separator", "\n");
        try {
            randomFile = new RandomAccessFile(file, "rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write((lineSeparator+str).getBytes(encoding));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                randomFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 快速清空一个超大的文件
     */
    public static boolean cleanFile(File file) {
        FileWriter fw = null;
        try {
            fw=new FileWriter(file);
            fw.write("");
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 获取文件的Mime类型
     * @param file
     * @return
     * @throws java.io.IOException
     */
    public static String MimeType(String file) throws java.io.IOException {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(file);
        return type;
    }
    /**
     * 获取文件的类型
     * @Summary:只利用文件头做判断故不全
     */
    public static String FileType(File file) {
        return FileTypeImpl.getFileType(file);
    }

    /**
     * 获取文件最后的修改时间
     */
    public static Date modifyTime(File file) {
        Date date = new Date(file.lastModified());
        return date;
    }

    /**
     * 获取文件的Hash
     */
    public static String hash(File file) {
        return SecUtil.FileMD5(file);
    }







    /**
     * 复制文件
     */
    public static boolean copy(String resourcePath, String targetPath) {
        File file = new File(resourcePath);
        return copy(file, targetPath);
    }

    /**
     * 复制文件
     *      通过该方式复制文件文件越大速度越是明显
     */
    public static boolean copy(File file, String targetFile) {
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try {
            fin = new FileInputStream(file);
            fout = new FileOutputStream(new File(targetFile));
            FileChannel in = fin.getChannel();
            FileChannel out = fout.getChannel();
            //设定缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (in.read(buffer) != -1) {
                buffer.flip();//准备写入，防止其他读取，锁住文件
                out.write(buffer);
                buffer.clear();//准备读取。将缓冲区清理完毕，移动文件内部指针
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                fout.close();
                fin.close();
            } catch (Exception e) {

            }
        }
        return false;
    }

    /**
     * 获取文件的编码(cpDetector)探测
     */
    public static String cpdetector(File file) {
        try {
            return FileImpl.cpdetector(file.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 利用简单的文件头字节特征探测文件编码
     *
     * @return UTF-8 Unicode UTF-16BE GBK
     */
    public static String SimpleEncoding(String file) {
        try {
            return FileImpl.SimpleEncoding(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建多级目录
     *
     * @param paths
     * @return
     */
    public static void createPaths(String paths) {
        File dir = new File(paths);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 创建文件支持多级目录
     */
    public static boolean createFiles(String filePaht) {
        File file = new File(filePaht);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            file.createNewFile();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除一个文件
     * @param file
     * @return
     */
    public static boolean deleteFile(File file){
        return file.delete();
    }

    /**
     * 删除一个目录
     */
    public static boolean deleteDir(File file){
        List<File> files = listFileAll(file);
        if(ValidUtil.isValid(files)){
            for(File f:files){
                if(f.isDirectory()){
                    deleteDir(f);
                }else{
                    deleteFile(f);
                }
            }
        }
        return file.delete();
    }


    /**
     * 快速的删除超大的文件
     * @param file
     * @return
     */
    public static boolean deleteBigFile(File file){
        if(cleanFile(file)){
            return file.delete();
        }
        return false;
    }

    /**
     *  罗列指定路径下的全部文件
     * @param path
     * @return 包含所有文件的的list
     */
    public static List<File> listFile(String path){
        File file = new File(path);
        return listFile(file);
    }

    /**
     * 复制目录
     * @param filePath
     * @param targetPath
     */
    public static void copyDir(String filePath,String targetPath){
        File file = new File(filePath);
        copyDir(file,targetPath);
    }
    /**
     * 复制目录
     * @param filePath
     * @param targetPath
     */
    public static void copyDir(File filePath,String targetPath){
        File targetFile = new File(targetPath);
        if(!targetFile.exists()){
            createPaths(targetPath);
        }
        File[] files = filePath.listFiles();
        if(ValidUtil.isValid(files)) {
            for (File file : files) {
                String path = file.getName();
                if (file.isDirectory()) {
                    copyDir(file, targetPath + "/" + path);
                } else {
                    copy(file,targetPath+"/"+path);
                }
            }
        }
    }

    /**
     *  罗列指定路径下的全部文件
     * @param path
     * @return
     */
    public static List<File> listFile(File path) {
        List<File> list = new ArrayList<File>();
        File[] files = path.listFiles();
        if(ValidUtil.isValid(files)) {
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
     * 罗列指定路径下的全部文件包括文件夹
     * @param path
     * @return
     */
    public static List<File> listFileAll(File path) {
        List<File> list = new ArrayList<File>();
        File[] files = path.listFiles();
        if(ValidUtil.isValid(files)) {
            for (File file : files) {
                list.add(file);
                if (file.isDirectory()) {
                    list.addAll(listFileAll(file));
                }
            }
        }
        return list;
    }




    /**
     * 获取指定目录下的特点文件,通过后缀名过滤
     */
    public static List<File> listFileFilter(File dirPath, final String postfixs) {
        /*
        如果在当前目录中使用Filter讲只罗列当前目录下的文件不会罗列孙子目录下的文件
        FilenameFilter filefilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(postfixs);
            }
        };
        */
        List<File> list = new ArrayList<File>();
        File[] files = dirPath.listFiles();
        if(ValidUtil.isValid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileFilter(file, postfixs));
                } else {
                    String fileName = file.getName().toLowerCase();
                    if(fileName.endsWith(postfixs.toLowerCase())) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 在指定的目录下搜寻文个文件
     */
    public static List<File> searchFile(File dirPath, String fileName) {
        List<File> list = new ArrayList<File>();
        File[] files = dirPath.listFiles();
        if(ValidUtil.isValid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(searchFile(file, fileName));
                } else {
                    String Name = file.getName().toLowerCase();
                    if(Name.equals(fileName)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 查找符合正则表达式reg的的文件
     * @param dirPath
     * @param reg
     * @return
     */
    public static List<File> searchFileReg(File dirPath,String reg){
        List<File> list = new ArrayList<File>();
        File[] files = dirPath.listFiles();
        if(ValidUtil.isValid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(searchFile(file, reg));
                } else {
                    String Name = file.getName();
                    if(RegUtil.isMatche(Name,reg)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

}