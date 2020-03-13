package com.joewang.repast.redis;

import com.joewang.repast.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.joewang.repast.staticstatus.StaticCode.*;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-13
 */
@Service
public class RedisService<T> {
    private RedisSerializer keySerializer = null;

    @Autowired
    private Jedis jedis;

    /**
     * 1.在分组开发的阶段，每一个人都去操作redis
     * 张三使用了key: goods
     * 李四不知道，李四也使用了这个key
     * --->李四覆盖了张三的key值--->张三的数据就丢失了
     * 存入redis的时候要检测，key是否存在(难点)
     * key存在，但是没有数据--->nil
     * key不存在--->nil
     *
     * 2.如果张三自己想要去覆盖key为goods的数据也可以
     *
     * 3.存数据的时候直接上失效时间，或者不设置失效时间
     *
     * 4.在设置失效时间的时候是否可以控制单位是秒还是毫秒
     */
    /**
     * @author Seven Lee
     * @description
     *      向redis中保存数据
     *      key:就是redis的key
     *      value:就是所要保存的数据
     *      nxxx:只能传两个值(nx, xx)
     *          "nx":redis中没有这个key的时候才进行保存数据
     *          "xx":redis中有key的时候才会保存数据(覆盖数据)
     *      expx:时间的单位(这里也只有两个固定值)
     *          "ex":失效时间的单位是秒
     *          "px":失效时间的单位是毫秒
     *      time:失效时间
     *          time缺省则是不设置失效时间
     *
     *   //TODO 会用这个方法实现分布式锁(这个方法是自动上锁的)
     *
     * @params [ key, value, nxxx, expx, time]
     * @date 2020/3/13
     * @return java.lang.String
     * @throws
     **/
    public String set(String key, T value, String nxxx, String expx, Integer time) {
        nxxx = nxxx.toLowerCase();
        if((null != time && 0 < time) && (NX.equals(nxxx) || XX.equals(nxxx)) &&
                (EX.equals(expx) || PX.equals(expx))) {
            // 说明需要去设置失效时间
            return jedis.set(key, JSONUtil.toJsonString(value), nxxx, expx, time);
        } else {
            // 说明不需要设置失效时间
            // 再次判断到底是覆盖，还是不覆盖
            if(NX.equals(nxxx)) {
                // 说明redis中有这个key则不保存
                return String.valueOf(jedis.setnx(key, JSONUtil.toJsonString(value)));
            } else if(XX.equals(nxxx)) {
                // 说明可以直接覆盖
                return jedis.set(key, JSONUtil.toJsonString(value));
            }
        }
        return NO;
    }

    /**
     * @author Seven Lee
     * @description
     *      从redis中查询数据(单个对象)
     *      {"key", "123"}
     *      如果只是一个单纯的类型，这个不适用
     * @params [key]
     * @date 2020/3/13
     * @return T
     * @throws
     **/
    public T getObject(String key) {
        return (T) JSONUtil.toObject(jedis.get(key), Object.class);
    }

    /**
     * @author Seven Lee
     * @description
     *      查询String类型
     * @params [key]
     * @date 2020/3/13
     * @return java.lang.String
     * @throws
     **/
    public String getString(String key) {
        return jedis.get(key);
    }

    /**
     * @author Seven Lee
     * @description
     *      从redis中查询数据列表
     * @params [key]
     * @date 2020/3/13
     * @return java.util.List<T>
     * @throws
     **/
    public List<T> getList(String key) {
        return (List<T>) JSONUtil.toList(jedis.get(key), Object.class);
    }

    /**
     * @author Seven Lee
     * @description
     *      从redis中删除单个数据
     *      --->从前端所获取到的key的类型是Integer
     *      --->从数据库中所查询出来的主键是Long
     *      --->把其他类型转换为String
     * @params [key]
     * @date 2020/3/13
     * @return java.lang.Long
     * @throws
     **/
    public Long delOne(Object key) {
        // 所有的类型都可以转换为byte数组
        return jedis.del(rawKey(key));
    }

    /**
     * @author Seven Lee
     * @description
     *      从redis中删除多个数据
     * @params [keys]
     * @date 2020/3/13
     * @return java.lang.Long
     * @throws
     **/
    public Long delMany(Collection<T> keys) {
        if(CollectionUtils.isEmpty(keys)) {
            return 0L;
        } else {
            // 把keys转换为byte的二维数组进行传递
            return jedis.del(this.rawKeys(keys));
        }
    }

    /**
     * @author Seven Lee
     * @description
     *      设置redis的失效时间
     * @params [key, time, expx]
     * @date 2020/3/13
     * @return java.lang.Long
     * @throws
     **/
    public Long expire(String key, Integer time, String expx) {
        if(EX.equals(expx)) {
            // 所使用的单位是秒
            return jedis.expire(key, time);
        } else if(PX.equals(expx)) {
            Long millTime = Long.parseLong(String.valueOf(time));
            return jedis.pexpire(key, millTime);
        }
        return 0L;
    }

    /**
     * @author Seven Lee
     * @description
     *      把Object对象转换为字节数组
     *      灵感来源于:Springboot的RedisTemplate
     * @params [key]
     * @date 2020/3/13
     * @return byte[]
     * @throws
     **/
    private byte[] rawKey(Object key) {
        Assert.notNull(key, "不规范的key值");
        return this.keySerializer == null && key instanceof byte[] ? (byte[])key :
                this.keySerializer.serialize(key);
    }

    /**
     * @author Seven Lee
     * @description
     *      把集合转换为二维字节数组
     * @params [keys]
     * @date 2020/3/13
     * @return byte[][]
     * @throws
     **/
    private byte[][] rawKeys(Collection<T> keys) {
        byte[][] rawKeys = new byte[keys.size()][];
        int i = 0;
        Object key;
        for(Iterator index = keys.iterator(); index.hasNext(); rawKeys[i++] = this.rawKey(key)) {
            key = index.next();
        }
        return rawKeys;
    }
}
