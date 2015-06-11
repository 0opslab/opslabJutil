package evilp0s.algorithmImpl;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * 封装了集中常用的文件读的方法
 */
public class FileReadImpl {

    /**
     * 利用FileChannel直接实现文件的对拷,对于大文件速度特别明显
     * @param source
     * @param target
     */
    public static void nioTransferCopy(File source, File target) {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inStream);
            close(in);
            close(outStream);
            close(out);
        }
    }

    /**
     * 使用FileChannel+Buffer实现文件的读取拷贝是一种极佳的方案
     * @param source
     * @param target
     */
    private static void nioBufferCopy(File source, File target) {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            while (in.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inStream);
            close(in);
            close(outStream);
            close(out);
        }
    }

    /**
     * 利用Buffer实现文件的读取拷贝
     * @param source
     * @param target
     */
    public static void customBufferBufferedStreamCopy(File source, File target) {
        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(source));
            fos = new BufferedOutputStream(new FileOutputStream(target));
            byte[] buf = new byte[4096];
            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis);
            close(fos);
        }
    }

    /**
     * 利用Buffer实现文件的读取拷贝
     * @param source
     * @param target
     */
    public static void customBufferStreamCopy(File source, File target) {
        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            byte[] buf = new byte[4096];
            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis);
            close(fos);
        }
    }

    private static void close(InputStream in){
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(OutputStream out){
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(Channel channel){
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
