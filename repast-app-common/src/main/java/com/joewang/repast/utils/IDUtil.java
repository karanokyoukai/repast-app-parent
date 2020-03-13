package com.joewang.repast.utils;

import java.util.UUID;

/**
 * @description:
 *      uuid生成工具类
 * @author: Joe Wang
 * @date: 2020-03-10
 */
public class IDUtil {
    public static String getUUID(){
        //因为如果生成的uuid中有"-"符号时在mysql数据库中是无法查询的，所以要去掉
        return UUID.randomUUID().toString().replace("-", "");
    }
}
