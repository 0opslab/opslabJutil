package com.opslab.annotion;

import java.lang.annotation.*;

/**
 * 用于标记业务主键的注解,常用于参数
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BusId {
    String value() default "";
}
