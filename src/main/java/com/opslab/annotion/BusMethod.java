package com.opslab.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法级上的注解
 */
@Target(value=ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BusMethod {
	/**
	 * 方法功能简介
	 */
	String name() default "";

	/**
	 * 功能
	 */
	MethodType action() default MethodType.ACCESS;


	/**
	 * 是否保存请求的参数
	 */
	boolean saveParams() default true;
}
