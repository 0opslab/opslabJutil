package com.opslab.helper;

import com.opslab.functions.ObjectHandler;
import com.opslab.functions.ObjectProcess;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 一些操作文件的便捷方法
 */
public final class FileHelper {
    private static Logger logger = Logger.getLogger(FileHelper.class);

    /**
     * 逐行处理
     * @param file handler file
     * @param encoding file encoding
     * @param handler handler
     */
    public static void handlerWithLine(File file, String encoding, ObjectHandler<String> handler){
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                handler.handler(line);
            }
        } catch (IOException e) {
            logger.error("handler error:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 逐行处理
     * @param file 需要处理的文件
     * @param encoding 文件编码
     * @param result 接受处理结果的集合
     * @param process 处理过程实现
     * @param <E>
     */
    public static <E> void  processWithLine(File file,String encoding, Collection<E> result, ObjectProcess<String,E> process) {
        if(result == null){
            logger.info("receive collection is null");
            return;
        }
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                E tmpLine = process.process(line);
                if(tmpLine != null){
                    result.add(tmpLine);
                }
            }
        } catch (IOException e) {
            logger.error("process error:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
