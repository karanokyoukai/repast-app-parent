package com.joewang.repast.constants;


import java.math.BigDecimal;

/**
 * <p>
 * 系统常量池
 * </p>
 *
 * @author JoeWang
 * @version v_1.0.0
 * @date 2020/6/12 9:55
 */
public interface DefaultConstants {

    /**
     * 超级管理员账号
     */
    String SUPER_ADMINISTRATOR_ACCOUNT = "his-admin";

    /**
     * 平台groupId
     */
    Long PLATFORM_GROUP_ID = 1000L;
    /**
     * 平台ORG_ID
     */
    Long PLATFORM_ID = 1001L;

    /**
     * 平台默认用户ID
     */
    Long PLATFORM_USER_ID = 10001L;

    /**
     * 默认密码
     */
    String DEFAULT_PWD = "000000";

    /**
     * 默认当前是第1页
     */
    Integer DEFAULT_PAGE_NO = 1;

    /**
     * 默认每页显示条数
     */
    Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 默认list查询数
     */
    Integer DEFAULT_BATCH_SIZE = 5000;

    /**
     * 是否删除(0:否 )
     */
    Integer NO_DELETED = 0;
    /**
     * 是否删除(1:是)
     */
    Integer DELETED = 1;

    /**
     * 是否默认(0:否)
     */
    Integer NO_DEFAULT = 0;
    /**
     * 是否默认(1:是)
     */
    Integer IS_DEFAULT = 1;

    /**
     * 停用标志(0 : 在用 ）
     */
    Integer NO_STOPED = 0;
    /**
     * 停用标志(1 : 停用 ）
     */
    Integer STOPED = 1;

    /**
     * 职工业务(1:线下 2:线上 3:全部)
     */
    Integer STAFF_OFF = 1;
    Integer STAFF_ON = 2;
    Integer STAFF_ALL = 3;

    /**
     * 审核状态(0:未审核 1:已审核)
     */
    Integer SHEET_CHECKED_NO = 0;
    Integer SHEET_CHECKED_YES = 1;

    /*
      是否可选项目
     */
    /**
     * 可选项目
     */
    Integer CAN_BE_SELECT = 1;
    /**
     * 必选项目
     */
    Integer SELECTED = 0;

    /**
     * 每隔10000条存储数据库，然后清理list，方便内存回收
     */
    Integer DEFAULT_BATCH_COUNT = 10000;

    /**
     * 文件是否加密, 1:加密 0:不加密
     */
    Integer FILE_ENCRYPT_TRUE = 1;
    Integer FILE_ENCRYPT_FALSE = 0;

    /**
     * 默认缓存大小 8192
     */
    int BUFFER_SIZE = 2 << 12;

    /**
     * 说明字数最大值
     */
    Integer MAX_PARAMETER_EXPLAIN = 50;

    /**
     * 说明字数最大值
     */
    Integer MAX_EXPLAIN = 30;

    /**
     * 套餐名称最大长度
     */
    Integer MAX = 10;

    /**
     * 否
     */
    Integer NO = 0;

    /**
     * 是
     */
    Integer YES = 1;

    /**
     * 输入习惯(0 : 拼音 1 : 五笔)
     */
    Integer PINYIN = 0;

    /**
     * 输入习惯(0 : 拼音 1 : 五笔)
     */
    Integer WUBI = 1;

    /**
     * 参数开启：Y
     */
    String OPEN = "Y";

    /**
     * 参数关闭：N
     */
    String CLOSE = "N";

    /**
     * 默认常量
     */
    Integer DEFAULT_0 = 0;
    Integer DEFAULT_1 = 1;
    Integer DEFAULT_2 = 2;
    Integer DEFAULT_3 = 3;
    Integer DEFAULT_4 = 4;

    /**
     * 默认地址数
     */
    Integer DEFAULT_ADDRESS_COUNT = 6;

    /**
     * 线下
     */
    Integer IS_NOT_ONLINE = 0;
    /**
     * 线上
     */
    Integer IS_ONLINE = 1;

    /**
     * 关
     */
    String CLOSE_WORD = "关";

    /**
     * 开
     */
    String OPEN_WORD = "开";

    String N = "N";

    String Y = "Y";

    String RESPONSE_OK_CODE = "0";

    /**
     * 无意义
     */
    String NONE = "无";
    Integer NONE_INT = -1;
    BigDecimal NONE_D = new BigDecimal("-0.0");

    /**
     * 年龄后缀-岁
     */
    String AGE_SUFFIX_YEAR = "岁";
}
