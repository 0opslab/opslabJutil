package com.opslab.annotion;

import java.lang.annotation.*;

/**
 * 用于缓存的注解,常用于业务方法
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BusCache {
    /**
     * 缓存值主键前缀
     */
    String prefix();

    /**
     * 缓存时间默认-1 单位未秒
     */
    long expire() default -1L;
}
