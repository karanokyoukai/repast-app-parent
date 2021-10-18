package com.joewang.repast.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author cczhaoyc@163.com
 * @version v_1.0.0
 * @description 基于Java8的时间工具类
 * @date 2020/4/22 10:35
 */
public class DateUtils {

    /**
     * 例如:2020-05-15
     */
    public static final String DATE = "yyyy-MM-dd";
    /**
     * 例如:2020-05-15 10:00
     */
    public static final String DATE_TIME_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";
    /**
     * 例如:2020-05-15 10:00:00
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 例如:20200515100000
     */
    public static final String DATE_TIME_UNSIGNED = "yyyyMMddHHmmss";

    /**
     * 例如:20200515100000
     */
    public static final String DATE_TIME_COMPACT = "yyyyMMdd";
    /**
     * 例如:10:00:00
     */
    public static final String TIME = "HH:mm:ss";
    /**
     * 例如:10:00
     */
    public static final String TIME_WITHOUT_SECOND = "HH:mm";
    /**
     * 世界标准时间:T表示分隔符,Z表示的是UTC
     * 例如:2020-07-01T16:00:00.000Z
     */
    public static final String UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS Z";
    /**
     * 例如:05.15
     */
    public static final String SHORT_DATE = "MM.dd";
    /**
     * 例如:05-15
     */
    public static final String SHORT_DATE_2 = "MM-dd";

    /**
     * eg:2021年9月
     */
    public static final String MONTH_CN = "yyyy年MM月";

    /**
     * 时间格式为YYYY-MM-DD
     */
    public static final SimpleDateFormat sdfymd = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 时间格式为YYYY-MM-DD HH:MM:SS
     */
    public static final SimpleDateFormat sdfymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取年
     *
     * @return 年
     */
    public static int getYear() {
        LocalDateTime localTime = LocalDateTime.now();
        return localTime.get(ChronoField.YEAR);
    }

    /**
     * 获取月份
     *
     * @return 月份
     */
    public static int getMonth() {
        LocalDateTime localTime = LocalDateTime.now();
        return localTime.get(ChronoField.MONTH_OF_YEAR);
    }

    /**
     * 获取某月的第几天
     *
     * @return 几号
     */
    public static int getMonthOfDay() {
        LocalDateTime localTime = LocalDateTime.now();
        return localTime.get(ChronoField.DAY_OF_MONTH);
    }

    /**
     * 格式化日期为字符串
     *
     * @param date    date
     * @param pattern 格式
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime string2LocalDateTIme(String dateString, String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateString, dtf);
    }

    /**
     * 解析字符串日期为Date
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return Date
     */
    public static Date parse(String dateStr, String pattern) {

        /*LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);*/

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
                .toFormatter()
                .withZone(ZoneId.of("Europe/Berlin"));
        OffsetDateTime offsetDateTime = ZonedDateTime.parse(dateStr, formatter).toOffsetDateTime();
        return Date.from(offsetDateTime.toInstant());
    }

    /**
     * <p>
     * 转LocalDate
     * <p>
     *
     * @author cz
     * @date 2021/7/2 13:51
     */
    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * <p>
     * 转LocalDate
     * <p>
     *
     * @author cz
     * @date 2021/7/2 13:51
     */
    public static LocalDate parseLocalDate(String dateStr, DateTimeFormatter formatter) {
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * 为Date增加分钟,减传负数
     *
     * @param date        日期
     * @param plusMinutes 要增加的分钟数
     * @return 新的日期
     */
    public static Date addMinutes(Date date, Long plusMinutes) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime newDateTime = dateTime.plusMinutes(plusMinutes);
        return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 增加时间
     *
     * @param date date
     * @param hour 要增加的小时数
     * @return new date
     */
    public static Date addHour(Date date, Long hour) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusHours(hour);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @return 返回当天的起始时间
     */
    public static Date getStartTime() {
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        return localDateTime2Date(now);
    }


    /**
     * @return 返回当天的结束时间
     */
    public static Date getEndTime() {
        LocalDateTime now = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
        return localDateTime2Date(now);
    }

    /**
     * 减月份
     *
     * @param monthsToSubtract 月份
     * @return Date
     */
    public static Date minusMonths(long monthsToSubtract) {
        LocalDateTime localDateTime = LocalDateTime.now().minusMonths(monthsToSubtract);
        return localDateTime2Date(localDateTime);
    }

    /**
     * LocalDate类型转为Date
     *
     * @param localDate LocalDate object
     * @return Date object
     */
    public static Date localDate2Date(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDateTime类型转为Date
     *
     * @param localDateTime LocalDateTime object
     * @return Date object
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * <p>
     * Date2LocalDate
     * </p>
     *
     * @author cczhaoyc@163.com
     * @date 2021/2/27 16:39
     */
    public static LocalDate date2LocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * <p>
     * Date2LocalDateTime
     * </p>
     *
     * @author cczhaoyc@163.com
     * @date 2021/2/27 16:40
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 查询当前年的第一天
     *
     * @param pattern 格式，默认格式 yyyy-MM-dd
     * @return 2020-05-15
     */
    public static String getFirstDayOfCurrentYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().withMonth(1).withDayOfMonth(1);
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATE;
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式 yyyy-MM-dd
     * @return 2020-05-15
     */
    public static String getLastMonthFirstDayOfPreviousYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1L).withMonth(12).withDayOfMonth(1);
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATE;
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式 yyyy-MM-dd
     * @return 2020-05-15
     */
    public static String getLastMonthLastDayOfPreviousYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1L).with(TemporalAdjusters.lastDayOfYear());
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATE;
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 获取当前日期
     *
     * @param pattern 格式，默认格式 yyyy-MM-dd
     * @return 2020-05-15
     */
    public static String getCurrentDay(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATE;
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 获取当前时间
     */
    public static String currentDateTime(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 时间格式2019-06-27T16:00:00.000Z转换为北京时间
     */
    public static String internationalTimeToLocalDate(String internationalTime) {
        if (StringUtils.isNotBlank(internationalTime)) {
            String TimeStart = internationalTime.replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat(UTC);

            String preCallbackTime = "";
            try {
                String callbackTimeStart = format.parse(TimeStart).toString();
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
                TimeZone tz = TimeZone.getTimeZone("GMT+8");
                sdf.setTimeZone(tz);
                Date parse = sdf.parse(callbackTimeStart);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME);
                preCallbackTime = simpleDateFormat.format(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return preCallbackTime;
        }
        return null;
    }

    /**
     * 获取本月第一天
     */
    public static LocalDate firstDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        //本月第一天
        return LocalDate.of(localDate.getYear(), localDate.getMonthValue(), 1);
    }

    /**
     * 获取本月最后一天
     */
    public static LocalDate lastDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        //本月的最后一天
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }


    /**
     * @param
     * @return java.lang.String
     * @description 获取8位数随机码
     * @author JoeWang
     * @date 2020/5/22
     */
    public static String getHex() {
        return Integer.toHexString((int) System.nanoTime());
    }

    /**
     * Description: 出生日期转年龄 （yyyy-MM-dd ->?天）
     *
     * @param birthDay
     * @return: java.lang.String
     * @Author: dk
     * @Date: 2021-01-21
     */
    public static String changeBirthDayToAge(LocalDate birthDay) {
        String agestr = "";
        LocalDate now = LocalDate.now();
        long bitrhdaymills = birthDay.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        LocalDate birth3year = birthDay.plusMonths(36L);
        long threeyearmills = birth3year.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long nowmills = now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long l = nowmills - bitrhdaymills;
        long day = l / (24 * 60 * 60 * 1000);

        Long l3 = nowmills - threeyearmills;
        long day3 = l3 / (24 * 60 * 60 * 1000);

        LocalDate birth6year = birthDay.plusYears(6L);
        long sixyearmills = birth6year.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long l6 = nowmills - sixyearmills;
        long day6 = l6 / (24 * 60 * 60 * 1000);
        if (day > 0 && day < 30) {
            Long i = 0L;
            while (i < 30L) {
                LocalDate sdate = birthDay.plusDays(i);
                if (sdate.isAfter(now)) {
                    agestr = (i - 1L) + "天";
                    break;
                }
                i++;
            }

        }
        if (day > 29 && day3 < 0 && day6 < 0) {
            Long i = 0L;
            while (i < 36L) {
                LocalDate sdate = birthDay.plusMonths(i);
                if (sdate.isAfter(now)) {
                    agestr = (i - 1L) + "月";
                    break;
                }
                i++;
            }

        }
        if (day3 > 0 && day6 < 0) {
            Long i = 0L;
            while (i < 6L) {
                LocalDate sdate = birthDay.plusYears(i);
                if (sdate.isAfter(now)) {
                    agestr = (i - 1L) + "岁月";
                    break;
                }
                i++;
            }

        }
        if (day6 > 0) {
            Long i = 0L;
            while (true) {
                LocalDate sdate = birthDay.plusYears(i);
                if (sdate.isAfter(now)) {
                    agestr = (i - 1L) + "岁";
                    break;
                }
                i++;
            }
        }
        System.out.println(agestr);
        return agestr;
    }

    /**
     * <p>
     * 格式化 LocalDateTime
     * <p>
     *
     * @author cz
     * @date 2021/3/8 13:52
     */
    public static String formatLocalDateTime(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * <p>
     * 格式化 LocalDate
     * <p>
     *
     * @author cz
     * @date 2021/3/8 13:52
     */
    public static String formatLocalDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * <p>
     * 是否为当天
     * <p>
     *
     * @author cz
     * @date 2021/6/21 10:31
     */
    public static boolean isToday(LocalDateTime date) {
        return LocalDate.now().compareTo(date.toLocalDate()) == 0;
    }

    /**
     * <p>
     * 将 LocalDate 格式化为 2021-09-01 00:00:00
     * <p>
     * @author cz
     * @date 2021/9/26 20:41
     */
    public static String formatLocalDateOf0(LocalDate date) {
        return formatLocalDateTime(LocalDateTime.of(date, LocalTime.of(0, 0, 0)), DATE_TIME);
    }

    /**
     * <p>
     * 将 LocalDate 格式化为 2021-09-01 23:59:59
     * <p>
     * @author cz
     * @date 2021/9/26 20:41
     */
    public static String formatLocalDateOf23(LocalDate date) {
        return formatLocalDateTime(LocalDateTime.of(date, LocalTime.of(23, 59, 59)), DATE_TIME);
    }


    /**
     * <p>
     * 获取上周开始时间
     * </p>
     *
     * @author cczhaoyc@163.com
     * @date 2021/2/27 16:17
     */
    public static Date lastWeekStartTime() {
        Map<String, Timestamp> week = DateUtils.lastWeek();
        Timestamp weekStart = week.get("weekStart");
        return new Date(weekStart.getTime());
    }

    /**
     * <p>
     * 获取上周结束时间
     * </p>
     *
     * @author cczhaoyc@163.com
     * @date 2021/2/27 16:17
     */
    public static Date lastWeekEndTime() {
        Map<String, Timestamp> week = DateUtils.lastWeek();
        Timestamp weekEnd = week.get("weekEnd");
        return new Date(weekEnd.getTime());
    }

    /**
     * <p>
     * 获取本周开始时间
     * </p>
     *
     * @author cczhaoyc@163.com
     * @date 2021/2/27 16:17
     */
    public static Date thisWeekStartTime() {
        Map<String, Timestamp> week = DateUtils.thisWeek();
        Timestamp weekStart = week.get("weekStart");
        return new Date(weekStart.getTime());
    }

    /**
     * <p>
     * 获取本周结束时间
     * </p>
     *
     * @author cczhaoyc@163.com
     * @date 2021/2/27 16:17
     */
    public static Date thisWeekEndTime() {
        Map<String, Timestamp> week = DateUtils.thisWeek();
        Timestamp weekEnd = week.get("weekEnd");
        return new Date(weekEnd.getTime());
    }

    /**
     * <p>
     * 获取上周时间
     * </p>
     *
     * @author cczhaoyc@163.com
     * @date 2021/2/27 16:32
     */
    private static Map<String, Timestamp> lastWeek() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");//注意后面有一个空格
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        calStart.add(Calendar.DAY_OF_MONTH, -7);//上一周
        calEnd.add(Calendar.DAY_OF_MONTH, -7);
        calStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calEnd.setFirstDayOfWeek(Calendar.MONDAY);
        calEnd.set(Calendar.DAY_OF_WEEK, calEnd.getFirstDayOfWeek() + 6);
        Timestamp weekStart = Timestamp.valueOf(format.format(calStart.getTime()) + "00:00:00");//字符串类型转化为timestamp
        Timestamp weekEnd = Timestamp.valueOf(format.format(calEnd.getTime()) + "23:59:59");
        Map<String, Timestamp> week = new HashMap<>();
        week.put("weekStart", weekStart);
        week.put("weekEnd", weekEnd);
        return week;
    }

    /**
     * <p>
     * 获取本周开始时间、结束时间
     * </p>
     *
     * @author cczhaoyc@163.com
     * @date 2021/2/27 16:32
     */
    private static Map<String, Timestamp> thisWeek() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");//注意后面有一个空格
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();
        calStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calEnd.setFirstDayOfWeek(Calendar.MONDAY);
        calEnd.set(Calendar.DAY_OF_WEEK, calEnd.getFirstDayOfWeek() + 6);
        Timestamp weekStart = Timestamp.valueOf(format.format(calStart.getTime()) + "00:00:00");//字符串类型转化为timestamp
        Timestamp weekEnd = Timestamp.valueOf(format.format(calEnd.getTime()) + "23:59:59");
        Map<String, Timestamp> week = new HashMap<>();
        week.put("weekStart", weekStart);
        week.put("weekEnd", weekEnd);
        return week;
    }


    /**
     * <p>
     * 获取00:00:00点
     * </p>
     *
     * @author DK
     * @date 2021/3/19
     */
    public static String get00ForDay(String day) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdfymd.parse(day.replaceAll("\\/", "-")));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return sdfymdhms.format(calendar.getTime());
    }

    /**
     * <p>
     * 获取23:59:59点
     * </p>
     *
     * @author DK
     * @date 2021/3/19
     */
    public static String get23ForDay(String day) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdfymd.parse(day.replaceAll("\\/", "-")));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return sdfymdhms.format(calendar.getTime());
    }

    /**
     * 获取两个时间段内的月份的字符串集合
     *
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getMonthBetweenStr(String startTime, String endTime, String pattern) throws ParseException {
        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        SimpleDateFormat timePattern = new SimpleDateFormat(pattern);
        start.setTime(timePattern.parse(startTime));
        start.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), 1);
        end.setTime(timePattern.parse(endTime));
        end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), 2);
        while (start.before(end)) {
            result.add(sdf.format(start.getTime()));
            start.add(Calendar.MONTH, 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.parseLocalDate("2021-12-11"));
    }
}
