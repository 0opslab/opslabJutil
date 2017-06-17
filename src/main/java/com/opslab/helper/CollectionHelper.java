package com.opslab.helper;

import com.opslab.functions.ObjectHandler;
import com.opslab.functions.ObjectProcess;
import sun.java2d.loops.ProcessPath;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 集合对象的一些助手工具类
 */
public class CollectionHelper {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CollectionHelper.class);

    /**
     * 对集合中元素进行特定的处理
     * @param collection 集合
     * @param handler 实现特定处理的方法
     * @param <T>
     */
    public static <T> void handler(Collection<T> collection, ObjectHandler<T> handler) {
        if (collection == null || collection.size() == 0) {
            logger.info("collection is null or empyt");
        }
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            handler.handler(iterator.next());
        }
    }

    /**
     * 对集合中的元素进行特定的处理，并获得处理后的结果
     * @param collection 待处理的集合
     * @param result    接受处理后结果的集合
     * @param process   实现的特定处理
     * @param <T>
     * @param <E>
     */
    public static <T,E> void  process(Collection<T> collection, Collection<E> result, ObjectProcess process) {
        if (collection == null || collection.size() == 0) {
            logger.info("collection is null or empyt");
        }
        if(result == null){
            logger.info("receive collection is null");
            return;
        }
        Iterator<T> iterator = collection.iterator();
        while(iterator.hasNext()){
            Object next = process.process(iterator.next());
            if(next != null){
                result.add((E)next);
            }
        }

    }
}
