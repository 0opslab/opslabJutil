package com.opslab.collection;

/**
 * <h6>Description:List过滤接口<h6>
 * <p></p>
 *
 * @date 2015-07-23.
 */
public interface ListFilter<T> {
    public boolean filter(T t);
}
