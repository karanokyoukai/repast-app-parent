package com.joewang.repast.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @description:
 *      cookie工具类
 * @author: Joe Wang
 * @date: 2020-03-10
 */
public class CookieUtil {

    /*
     * @desc: 获取cookie
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [request, cookieName]
     * @return: java.lang.String
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName){
        return getCookieValue(request , cookieName, false);
    }

    /*
     * @desc: 获取指定编码的cookie
     *      isDecoder：是否进行编码  如果传入的参数为true 会把cookie值自动转换为相应编码
     *      如果为false 会直接使用cookie当前的编码集
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [request, cookieName, isDecoder]
     * @return: java.lang.String
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder){
        Cookie[] cookies = request.getCookies();
        if (cookies ==null || cookieName == null){
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookies.length; i++){
                if (cookies[i].getName().equals(cookieName)){
                    if (isDecoder){
                        retValue = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                    }else {
                        retValue = cookies[i].getValue();
                    }
                }
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /*
     * @desc: 获取cookie并自定义编码集
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [request, cookieName, encodeString]
     * @return: java.lang.String
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString){
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null){
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookies.length; i++){
                if (cookies[i].getName().equals(cookieName)){
                    retValue = URLDecoder.decode(cookies[i].getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /*
     * @desc: 设置cookie 把cookie保存在浏览器内存中，一旦浏览器关闭就会失效
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [request, response, cookieName, cookieValue]
     * @return: void
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue){
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /*
     * @desc: 设置cookie并设置失效时间，会将cookie保存在本机硬盘中，时间一到自动失效
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [request, response, cookieName, cookieValue, cookieMaxage]
     * @return: void
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage){
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /*
     * @desc:
     *      设置cookie并且是否要把cookie的编码集改为utf-8
     *      cookie是存在浏览器内存中
     *      isEncode：true改变cookie编码集
     *          false使用cookie默认编码集
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [request, response, cookieName, cookieValue, cookieMaxage, isEncode]
     * @return: void
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, boolean isEncode){
        setCookie(request, response, cookieName, cookieValue, isEncode);
    }

    /**
     * 设置cookie，进行编码，设置最大失效时间，cookie存入本机硬盘
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * 设置cookie并且自定义编码，设置实现时间，cookie存入本机硬盘
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * 删除cookie并且删除cookie的域名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**
     * 设置Cookie的失效时间，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                          String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request) {
                // 设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Cookie的失效时间，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                          String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request) {// 设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println(domainName + "-----");
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到cookie的跨域
     */
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        System.out.println(serverName);
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            if (serverName.contains("127.0.0.1")) {
                domainName = "localhost";
            } else {
                final String[] domains = serverName.split("\\.");
                int len = domains.length;
                if (len > 3) {
                    // www.xxx.com.cn
                    domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
                } else if (len <= 3 && len > 1) {
                    // xxx.com or xxx.cn
                    domainName = "." + domains[len - 2] + "." + domains[len - 1];
                } else {
                    domainName = serverName;
                }
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
}
