package com.joewang.repast.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * @description:
 *      json转换工具类
 * @author: Joe Wang
 * @date: 2020-03-10
 */
public class JSONUtil {
    // 1.定义私有静态常量ObjectMapper(命名规则：所有字母全部大写，单词与单词之间使用_连接)
    // ObjectMapper:就是fastjson包中进行类型转换的工具类
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /*
     * @desc: 将对象转换为json字符串
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [object]
     * @return: java.lang.String
     */
    public static String toJsonString(Object object){
        try {
            String jsonString = OBJECT_MAPPER.writeValueAsString(object);
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * @desc:
     *      将json转换为指定对象
     *      <T> 定义一个类型
     *      T 返回值的类型
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [jsonData：传入的json对象, beanType：要转换的目标类型]
     * @return: T
     */
    public static <T> T toObject(String jsonData, Class<T> beanType){
        try {
            T t = OBJECT_MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * @desc:
     *      将json转换为List集合
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [jsonData, beanType]
     * @return: java.util.List<T>
     */
    public static <T> List<T> toList(String jsonData, Class<T> beanType){
        // 1.为List集合添加一个指定的泛型
        // List  User.class ---> 通过constructParametricType方法把List和User合并，也就是说为List指定一个User对象的泛型(List<User>)
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class,beanType);
        try {
            List<T> list = OBJECT_MAPPER.readValue(jsonData,javaType);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
