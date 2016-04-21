package com.opslab.collection;

import com.opslab.valid;

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
public class CollectionUtil {

    /**
     * 去重
     */
    public static <T> List<T> removeDuplicate(List<T> list) {
        Set  set     = new HashSet();
        List newList = new ArrayList();
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
    public static <T> List<T> Filter(List<T> list, ListFilter filter) {
        List result = new ArrayList();
        if (valid.valid(list)) {
            for (T t : list) {
                if (filter.filter(t)) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    public static <T> Set<T> Filter(Set<T> set, SetFilter filter) {
        Set result = new HashSet();
        if (valid.valid(set)) {
            for (T t : set) {
                if (filter.filter(t)) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    public static <T> Queue Filter(Queue<T> queue, QueueFilter filter) {
        Queue result = new LinkedList();
        if (valid.valid(queue)) {
            for (T t : queue) {
                if (filter.filter(t)) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    public static <K, V> Map Filter(Map<K,V> map, MapFilter filter) {
        Map result = new HashMap();
        if (valid.valid(map)) {
            for (Map.Entry<K,V> entry : map.entrySet()) {
                if (filter.filter(entry)) {
                    result.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return result;
    }

    /**
     * 求俩个集合的交集
     */
    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        if (valid.valid(list1, list2)) {
            Set<T> set = new HashSet<>(list1);
            set.retainAll(list2);
            return new ArrayList<>(set);
        }
        return new ArrayList<T>();
    }

    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        if (valid.valid(set1, set2)) {
            List<T> list = new ArrayList<T>(set1);
            list.retainAll(set2);
            return new HashSet<>(list);
        }
        return new HashSet<T>();
    }

    public static <T> Queue<T> intersection(Queue<T> queue1, Queue<T> queue2) {
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
    public static <K, V> Map<K,V> intersection(Map<K,V> map1, Map<K,V> map2) {
        Map<K,V> map = new HashMap<>();
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
    public static <T> List<T> unicon(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList();
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }

    public static <T> Set<T> unicon(Set<T> set1, Set<T> set2) {
        Set<T> set = new HashSet<>();
        set = set1;
        set.addAll(set2);
        return set;
    }

    public static <T> Queue<T> unicon(Queue<T> queue1, Queue<T> queue2) {
        Queue queue = new LinkedList();
        queue.addAll(queue1);
        queue.addAll(queue2);
        return queue;
    }

    public static <K, V> Map<K,V> unicon(Map<K,V> map1, Map<K,V> map2) {
        Map<K,V> map = new HashMap<>();
        map.putAll(map1);
        map.putAll(map2);
        return map;
    }

    ///////////////////////////////////////////////////////////////////////////////

    /**
     * 求俩个集合的差集
     */
    public static <T> List<T> subtract(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<>();
        if (valid.valid(list1)) {
            list.addAll(list1);
            list.removeAll(list2);
        }
        return list;
    }

    public static <T> Set<T> subtract(Set<T> set1, Set<T> set2) {
        Set<T> set = new HashSet<>();
        if (valid.valid(set1)) {
            set.addAll(set1);
            set.removeAll(set2);
        }
        return set;
    }

    public static <T> Queue<T> subtract(Queue<T> queue1, Queue<T> queue2) {
        Queue<T> queue = new LinkedList<>();
        if (valid.valid(queue1)) {
            queue.addAll(queue1);
            queue.removeAll(queue2);
        }
        return queue;
    }

    public static <K, V> Map<K,V> subtract(Map<K,V> map1, Map<K,V> map2) {
        Map<K,V> map = new HashMap<>();
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
}
