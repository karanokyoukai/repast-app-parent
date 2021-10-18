package com.joewang.repast.constants;

import java.util.concurrent.TimeUnit;

/**
 * @author cczhaoyc@163.com
 * @version v_1.0.0
 * @description Redis常量
 * @date 2020/4/22 15:09
 */
public interface RedisConstants {

    /**
     * key分隔符
     */
    String DELIMITER = ":";

    /**
     * 默认缓存失效时间2小时
     */
    Long DEFAULT_EXPIRE_TIME = TimeUnit.HOURS.toSeconds(2);

    /**
     * 缓存失效时间10小时
     */
    Long EXPIRE_TIME_10_HOURS = TimeUnit.HOURS.toSeconds(10);

    /**
     * 缓存失效时间10分钟
     */
    Long EXPIRE_TIME_10_MINUTES = TimeUnit.MINUTES.toSeconds(10);

    /**
     * 默认获取锁超时时间5秒
     */
    Long DEFAULT_TRY_LOCK_TIMEOUT = 5 * 1000L;

    /**
     * 缓存失效时间1天
     */
    Long EXPIRE_TIME_1_DAYS = TimeUnit.DAYS.toSeconds(1);

}
