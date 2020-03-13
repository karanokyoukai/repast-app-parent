package com.joewang.repast.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:  日期处理工具类
 * @author: Joe Wang
 * @date: 2020-03-10
 */
public class DateUtil {
    // 日期类型 yyyy-MM-dd
    public final static String DATE_TYPE = "yyyy-MM-dd";

    // 日期时间类型 yyyy-MM-dd HH:mm:ss
    public final static String DATE_TIME_TYPE = "yyyy-MM-dd HH:mm:ss";

    /*
     * @desc: 按照yyyy-MM-dd格式转换日期
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [date]
     * @return: java.lang.String
     */
    public static final String formatDate(Object date){
        if (date == null){
            return null;
        }else {
            return formatDate(date, DATE_TIME_TYPE);
        }
    }

    /*
     * @desc: 将时间转换为字符串（xx天，xx时，xx分，xx秒，大于360天显示日期时间）
     * @author: Joe Wang 
     * @date: 2020/3/10 
     * @param: [millisecond]
     * @return: java.lang.String 
     */
    public static String formatDateAgo(Long millisecond){
        StringBuilder stringBuilder = new StringBuilder();
        if (millisecond < 1000){
            stringBuilder.append(millisecond).append("毫秒");
        }else {
            Integer ss = 1000;
            Integer mi = ss * 60;
            Integer hh = mi * 60;
            Integer dd = hh * 24;

            Long day = millisecond / dd;
            Long hour = (millisecond - day * dd) / hh;
            Long minute = (millisecond -day * dd - hour * hh) / mi;
            Long second = (millisecond - day * dd - hour * hh - minute * mi) / ss;
            if (day > 365) {
                return DateUtil.formatDate(new Date(millisecond), "yyyy年MM月dd日 HH时mm分ss秒");
            }
            if (day > 0) {
                stringBuilder.append(day).append("天");
            }
            if (hour > 0) {
                stringBuilder.append(hour).append("小时");
            }
            if (minute > 0) {
                stringBuilder.append(minute).append("分钟");
            }
            if (second > 0) {
                stringBuilder.append(second).append("秒");
            }
        }
        return stringBuilder.toString();
    }

    /*
     * @desc: 按照指定格式转换日期
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: [date, formatType]
     * @return: java.lang.String
     */
    public static final String formatDate(Object date, String formatType) {
        if (date == null) {
            return null;
        } else {
            if (StringUtil.isNotEmpty(formatType)) {
                SimpleDateFormat format = new SimpleDateFormat(formatType);
                return format.format(date);
            } else {
                return formatDate(date);
            }
        }
    }

    /*
     * @desc: 获取当前时间
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: []
     * @return: java.lang.String
     */
    public static final String getDateNow() {
        return formatDate(new Date());
    }

    /*
     * @desc: 获取当前年份
     * @author: Joe Wang
     * @date: 2020/3/10
     * @param: []
     * @return: int
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
