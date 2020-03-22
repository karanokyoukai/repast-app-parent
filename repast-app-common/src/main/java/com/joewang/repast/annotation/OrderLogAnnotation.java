package com.joewang.repast.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zxz
 * @ClassName: OrderLogAnnotation
 * @Description:
 *      自定义订单日志注解
 * @date: create in 2020/3/20 18:47
 * @since JDK 1.8
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderLogAnnotation {

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
