package com.joewang.repast.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 *      返回结果集处理类
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@Data
public class ResultData<T> implements Serializable {
    private String code;
    private String msg;
    private String detail;
    private T data;
}
