package com.joewang.repast.utils;

/**
 * @author: dawang
 * @date: Created in 2020/3/14 23:47
 * @version: 1.0
 * @description:判断token是否存在
 */
public class DecideToken {
    public static Boolean decideToken(String token) {
        if (token != null && !token.equals("") && StringUtil.isNotEmpty(token)) {
            return true;
        }
        return false;
    }
}