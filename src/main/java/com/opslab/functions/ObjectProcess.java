package com.opslab.functions;

/**
 * 定义一些处理接口，便于优雅的处理一些数据,实现的方法返回数据
 */
public interface ObjectProcess<T, E> {
    /**
     * 实现自定义处理方法
     * @param t
     * @return
     */
    E process(T t);
}
