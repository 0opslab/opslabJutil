package com.opslab.util.collection;

import com.opslab.util.valid;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * 封装一些集合相关度工具类
 * <p/>
 * Collection<--List<--Vector
 * Collection<--List<--ArrayList
 * Collection<--List<--LinkedList
 * Collection<--Set<--HashSet
 * Collection<--Set<--HashSet<--LinkedHashSet
 * Collection<--Set<--SortedSet<--TreeSet
 * Map<--SortedMap<--TreeMap
 * Map<--HashMap
 */
public final class CollectionUtil {
    private static Logger logger = Logger.getLogger(CollectionUtil.class);

    /**
     * 去重
     */
    public final static <T> List<T> removeDuplicate(List<T> list) {

        List newList = new ArrayList();
        if (!valid.valid(list)) {
            logger.error("list is empty or is null");
            return newList;
        }
        Set set = new HashSet();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        return newList;
    }


    /**
     * 使用指定的Filter过滤集合
     */
    public final static <T> List<T> filter(List<T> list, ListFilter filter) {
        List result = new ArrayList();
        if (!valid.valid(list)) {
            logger.error("list is empty or is null");
            return result;
        }
        for (T t : list) {
            if (filter.filter(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public final static <T> Set<T> filter(Set<T> set, SetFilter filter) {
        Set result = new HashSet();
        if (valid.valid(set)) {
            logger.error("list is empty or is null");
            return result;
        }
        for (T t : set) {
            if (filter.filter(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public final static <T> Queue filter(Queue<T> queue, QueueFilter filter) {
        Queue result = new LinkedList();
        if (valid.valid(queue)) {
            logger.error("queue is empty or is null");
            return result;
        }
        for (T t : queue) {
            if (filter.filter(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public final static <K, V> Map Filter(Map<K, V> map, MapFilter filter) {
        Map result = new HashMap();
        if (valid.valid(map)) {
            logger.error("map is empty or is null");
            return result;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (filter.filter(entry)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    /**
     * 求俩个集合的交集
     */
    public final  static <T> List<T> intersection(List<T> list1, List<T> list2) {
        if (valid.valid(list1, list2)) {
            Set<T> set = new HashSet<>(list1);
            set.retainAll(list2);
            return new ArrayList<>(set);
        }
        return new ArrayList<T>();
    }

    public final static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        if (valid.valid(set1, set2)) {
            List<T> list = new ArrayList<T>(set1);
            list.retainAll(set2);
            return new HashSet<>(list);
        }
        return new HashSet<T>();
    }

    public final static <T> Queue<T> intersection(Queue<T> queue1, Queue<T> queue2) {
        if (valid.valid(queue1, queue2)) {
            Set<T> set = new HashSet<T>(queue1);
            set.retainAll(queue2);
            return new LinkedList<T>(set);
        }
        return new LinkedList<T>();
    }

    /**
     * Map集合的交集只提供键的交集
     *
     * @param map1
     * @param map2
     * @param <K>
     * @param <V>
     * @return
     */
    public final static <K, V> Map<K, V> intersection(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> map = new HashMap<>();
        if (valid.valid(map1, map2)) {
            Set<K> setkey1 = new HashSet<>(map1.keySet());
            Set<K> setkey2 = new HashSet<>(map2.keySet());
            setkey1.retainAll(setkey2);
            for (K k : setkey1) {
                map.put(k, map1.get(k));
            }
        }
        return map;
    }

    ///////////////////////////////////////////////////////////////////////////////

    /**
     * 求俩个集合的并集
     */
    public final static <T> List<T> unicon(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList();
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }

    public final static <T> Set<T> unicon(Set<T> set1, Set<T> set2) {
        Set<T> set = new HashSet<>();
        set = set1;
        set.addAll(set2);
        return set;
    }

    public final static <T> Queue<T> unicon(Queue<T> queue1, Queue<T> queue2) {
        Queue queue = new LinkedList();
        queue.addAll(queue1);
        queue.addAll(queue2);
        return queue;
    }

    public final static <K, V> Map<K, V> unicon(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> map = new HashMap<>();
        map.putAll(map1);
        map.putAll(map2);
        return map;
    }

    ///////////////////////////////////////////////////////////////////////////////

    /**
     * 求俩个集合的差集
     */
    public final static <T> List<T> subtract(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<>();
        if (valid.valid(list1)) {
            list.addAll(list1);
            list.removeAll(list2);
        }
        return list;
    }

    public final static <T> Set<T> subtract(Set<T> set1, Set<T> set2) {
        Set<T> set = new HashSet<>();
        if (valid.valid(set1)) {
            set.addAll(set1);
            set.removeAll(set2);
        }
        return set;
    }

    public final static <T> Queue<T> subtract(Queue<T> queue1, Queue<T> queue2) {
        Queue<T> queue = new LinkedList<>();
        if (valid.valid(queue1)) {
            queue.addAll(queue1);
            queue.removeAll(queue2);
        }
        return queue;
    }

    public final static <K, V> Map<K, V> subtract(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> map = new HashMap<>();
        if (valid.valid(map1, map2)) {
            Set<K> setkey1 = new HashSet<>(map1.keySet());
            Set<K> setkey2 = new HashSet<>(map2.keySet());
            for (K k : setkey2) {
                setkey1.remove(k);
            }
            for (K k : setkey1) {
                map.put(k, map1.get(k));
            }
        }
        return map;

    }

    /**
     * 将List以separator链接并以字符串的形式返回
     *
     * @param list
     * @param separator
     * @param <T>
     * @return
     */
    public final static <T> String join(List<T> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString()).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 将queue以separator链接并以字符串的形式返回
     *
     * @param queue
     * @param separator
     * @param <T>
     * @return
     */
    public final static <T> String join(Queue<T> queue, String separator) {
        StringBuilder sb = new StringBuilder();
        for (T t : queue) {
            sb.append(t.toString()).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - separator.length());
    }

    /**
     * 将set以separator链接并以字符串的形式返回
     *
     * @param set
     * @param separator
     * @param <T>
     * @return
     */
    public final static <T> String join(Set<T> set, String separator) {
        StringBuilder sb = new StringBuilder();
        for (T t : set) {
            sb.append(t.toString()).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - separator.length());
    }

    /**
     * 将map的key以separator链接并以字符串的形式返回
     *
     * @param map
     * @param separator
     * @param <K>
     * @param <V>
     * @return
     */
    public final static <K, V> String keyJoin(Map<K, V> map, String separator) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            sb.append(String.valueOf(entry.getKey())).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - separator.length());
    }

    /**
     * 将map的value以separator链接并以字符串的形式返回
     *
     * @param map
     * @param separator
     * @param <K>
     * @param <V>
     * @return
     */
    public final static <K, V> String valueJoin(Map<K, V> map, String separator) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<K, V> entry : map.entrySet()) {
            sb.append(String.valueOf(entry.getValue())).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - separator.length());
    }
}
