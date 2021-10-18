package com.joewang.repast.utils;


import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;
import java.time.Period;

/**
 * 年龄计算工具类
 *
 * @author xiaojh
 * @date 2021/03/18
 */
public class AgeUtils {

    /**
     * 病人年龄转换
     * 年龄字段仅做展示，不可修改。
     * <p>
     * a)  年龄不足1周岁的，以分数形式表示，分子为月数，分母为不足月的天数，其中分母最大为30。如3个月28天，显示为3/28。
     * <p>
     * b）年龄满1周岁的，以周岁展示。显示年龄+岁。
     *
     * @param birthDay
     * @return
     */
    public static String getPatientAge(LocalDate birthDay) {
        if (null == birthDay) {
            return "0/0";
        }

        // 如果年龄大于1岁直接返回
        int age = 0;
        try {
            age = DateUtil.ageOfNow(DateUtil.parse(birthDay.toString()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage(), e.getCause());
        }
        if (age > 0) {
            return String.valueOf(age);
        }

        LocalDate now = LocalDate.now();
        Period between = Period.between(birthDay, now);
        return between.getMonths() + "/" + between.getDays();
    }

    /**
     * <p>
     *  年龄字段仅做展示，不可修改。
     *     a)   天：24h<患者年龄≤29天的。
     *     b)   月：29天<患者年龄≤36个月。
     *     c)   岁：患者年龄>3岁。
     * </p>
     *
     * @param birthday 生日
     * @return 年龄
     */
    public static String publicPatientAge(LocalDate birthday){
        if(null == birthday){
            return "null";
        }

        // 相差时间
        LocalDate now = LocalDate.now();
        if(birthday.isAfter(now)){
            return "出生日期不能超过当前日期";
        }

        Period between = Period.between(birthday, now);

        int years = between.getYears();
        int months = between.getMonths();
        int days = between.getDays();

        // 患者年龄>3岁
        if(years > 3 || (years == 3 && (months > 0 || days >0))){
            return years + "岁";
        }

        // 24h<患者年龄≤29天的
        if(years == 0 && months == 0 && days <= 29){
            return days + "天";
        }

        // 29天<患者年龄≤36个月
        return (years * 12 + months) + "月";

    }
    /**
     * <p>
     *  年龄字段仅做展示，不可修改。
     *     a)   天：24h<患者年龄≤29天的。
     *     b)   月：29天<患者年龄≤12个月。
     *     c)   岁：患者年龄>1岁。
     * </p>
     *
     * @param birthday 生日
     * @return 年龄
     */
    public static String publicPatientAge4InsertPatient(LocalDate birthday){
        if(null == birthday){
            return "null";
        }

        // 相差时间
        LocalDate now = LocalDate.now();
        if(birthday.isAfter(now)){
            return "出生日期不能超过当前日期";
        }

        Period between = Period.between(birthday, now);

        int years = between.getYears();
        int months = between.getMonths();
        int days = between.getDays();

        // 患者年龄>1岁
        if(years > 1 || (years == 1 && (months > 0 || days >0))){
            return years + "岁";
        }

        // 24h<患者年龄≤29天的
        if(years == 0 && months == 0 && days <= 29){
            return days + "天";
        }

        // 29天<患者年龄≤12个月
        return (years * 12 + months) + "月";

    }

}
