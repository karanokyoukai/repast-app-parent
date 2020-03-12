package com.joewang.repast.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 *      自定义登录日志注解
 *      @Target--->这个就是自定义注解所要使用的范围目标(值可以有多个)
 *      @Retention:注解在什么时候会被加载:runtime--运行时被加载
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginLogAnnotation {
    /**
     * @desc: 执行的操作类型
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: []
     * @return: java.lang.String
     */
    String operationType() default ""; //default:叫做默认值，如果传递operationType的时候，直接使用的是默认值

    /**
     * @desc: 执行的操作名
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: []
     * @return: java.lang.String
     */
    String operationName() default "";
}
