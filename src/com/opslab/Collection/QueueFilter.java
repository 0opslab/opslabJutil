package com.opslab.collection;

/**
 * <h6>Description:Queue过滤接口<h6>
 * <p></p>
 *
 * @date 2015-07-23.
 */
public interface QueueFilter<T> {
    public boolean filter(T t);
}
