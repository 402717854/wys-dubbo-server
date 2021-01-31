package com.wys.dubbo.util;

import com.wys.dubbo.common.constant.RedisConstant;
import com.wys.dubbo.common.exception.DubboBusinessException;
import com.wys.dubbo.common.exception.DubboExceptionEnum;
import com.wys.dubbo.common.mybatis.IMBaseDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis工具
 * @Author: 86133
 * @Date: 2021/01/25
 */
@Slf4j
@Component
public final class RedisUtils {

    private static RedisTemplate redisTemplate;

    private static StringRedisTemplate stringRedisTemplate;

    //是否禁用redis缓存虚拟数据0不禁用(开启)1禁用(不开启)

    private static Integer redisVirtualDataState;

    /**
     * 利用set将RedisTemplate注入以便将redisTemplate设置为static这样在其他需要使用的地方就不用再注入了
     */
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
   }
    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisUtils.stringRedisTemplate = stringRedisTemplate;
   }

    @Value("${redis.virtualData.state:1}")
    public void setRedisVirtualDataState(Integer redisVirtualDataState) {
        RedisUtils.redisVirtualDataState = redisVirtualDataState;
   }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public static boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
        return result;
   }

    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    public static boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            return redisTemplate.expire(key, expireTime, timeUnit);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }

    /**
     * 写入缓存设置时效时间默认毫秒
     * @param key
     * @param value
     * @return
     */
    public static boolean setEX(final String key, Object value, Long expireTime) {
        try {
            return set(key, value, expireTime, TimeUnit.MILLISECONDS);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }

    /**
     * 批量删除对应的value
     * @param keys
     */
    public static void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
       }
   }

    /**
     * 批量删除key
     * @param pattern
     */
    public static void removePattern(final String pattern) {
        try {
            Set<Serializable> keys = redisTemplate.keys(pattern);
            if (keys.size() > 0) {
                redisTemplate.delete(keys);
           }
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * 删除对应的value
     * @param key
     */
    public static void remove(final String key) {
        try {
            if (exists(key)) {
                redisTemplate.delete(key);
           }
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public static boolean exists(final String key) {
        try {
            return redisTemplate.hasKey(key);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public static Object get(final String key) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            return operations.get(key);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public static void hmSet(String key, Object hashKey, Object value) {
        try {
            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
            hash.put(key, hashKey, value);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public static Object hmGet(String key, Object hashKey) {
        try {
            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
            return hash.get(key, hashKey);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }

    /**
     * 哈希删除数据
     * @param key
     * @param hashKey
     * @return
     */
    public static Object hmRemove(String key, Object hashKey) {
        try {
            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
            return hash.delete(key, hashKey);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public static void lPush(String k, Object v) {
        try {
            ListOperations<String, Object> list = redisTemplate.opsForList();
            list.rightPush(k, v);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public static List<Object> lRange(String k, long l, long l1) {
        try {
            ListOperations<String, Object> list = redisTemplate.opsForList();
            return list.range(k, l, l1);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * 集合添加
     * @param key
     * @param value
     */
    public static void setAdd(String key, Object value) {

        try {
            SetOperations<String, Object> set = redisTemplate.opsForSet();
            set.add(key, value);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * 集合获取
     * @param key
     * @return
     */
    public static Set<Object> setMembers(String key) {
        try {
            SetOperations<String, Object> set = redisTemplate.opsForSet();
            return set.members(key);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * 检查集合中是否存在该值
     * @param key
     * @param value
     */
    public static boolean isMember(String key, Object value) {

        try {
            SetOperations<String, Object> set = redisTemplate.opsForSet();
            return set.isMember(key, value);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }
    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public static void zAdd(String key, Object value, double scoure) {
        try {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            zset.add(key, value, scoure);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * @param
     * @param key
     * @param scoure
     * @param scoure1
     * @return java.lang.Long
     * @Description 获取分值在某区间内的值
     * @date 2020/3/4 23:06
     * @auther lizy
     */
    public static Long getZcount(String key, double scoure, double scoure1) {
        try {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            return zset.count(key, scoure, scoure1);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public static Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        try {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            return zset.rangeByScore(key, scoure, scoure1);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }

    /**
     * 有序集合获取(由大到小且指定条数)
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public static Set<Object> reverseRange(String key, Long scoure, Long scoure1) {
        try {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            return zset.reverseRange(key, scoure, scoure1);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * @param
     * @param key
     * @param value
     * @return java.lang.Double
     * @Description 获取指定zset的分值score
     * @date 2020/3/4 23:06
     * @auther lizy
     */
    public static Double getReportCategoryBuyCount(String key, Object value) {
        try {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            Double d = zset.score(key, value);
            return d == null ? 0 : d;
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * @param
     * @param key
     * @param value
     * @return java.lang.Long
     * @Description 获取某一元素的排名(由大到小)
     * @date 2020/3/5 8:09
     * @auther lizy
     */
    public static Long getZrevrankByValue(String key, Object value) {
        try {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            Long d = zset.reverseRank(key, value);
            return d == null ? 0 : d;
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * 增加数值
     */
    public static Double getZincrbyByValue(String key, Object value, double scoure) {

        try {
            ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
            return zset.incrementScore(key, value, scoure);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }

   }

    /**
     * @param mapper
     * @param id            主键Id也是查询数据库的Id
     * @param objectVirtual 需要作为虚拟数据的类
     * @return Object
     * @Description 查询缓存如果没有则从数据库中获取(使用selectPK ()),数据库中如果没有则设置一个默认的状态为禁用的数据
     * @Param key 为前缀 在方法中使用时会将key+id作为主键
     * @Date 11:46 2020/12/18
     **/
    public static Object queryAndSetSync(String key, IMBaseDAO mapper, Integer id, Object objectVirtual) {

        Object object = get(key + id);
        if (object == null) {
            synchronized (id) {
                object = RedisUtils.get(key + id);
                if (object != null) {
                    return object;
               }
                object = mapper.selectPk(id);
                if (object != null) {
                    RedisUtils.set(key + id, object);
               } else if (redisVirtualDataState == 0) {
                    Map<String,Object> map=new HashMap<>();
                    map.put("id",id);
                    //设置一个虚拟的为禁用的数据
                    RedisUtils.setEX(key + id, map, RedisConstant.VIRTUAL_EX);

                    return objectVirtual;
               }
           }
       }
        return object;
   }
    /**
     * 自增
     * @param key
     * @return
     */
    public static Long increment(final String key) {
        try {
            Long increment = redisTemplate.opsForValue().increment(key, 1);
            return increment;
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }
    /**
     * 设置key过期时间
     * @param key
     * @param expireTime
     * @param timeUnit
     * @return
     */
    public static boolean expire(final String key,Long expireTime, TimeUnit timeUnit) {
        try {
            return redisTemplate.expire(key, expireTime, timeUnit);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }

    /**
     * redis分布式锁
     * @param key
     * @param clientId
     * @param expire
     * @return
     */
    public static boolean lock(String key, String clientId, long expire){
        try {
            return stringRedisTemplate.opsForValue().setIfAbsent(key, clientId, expire, TimeUnit.MILLISECONDS);
       } catch (Exception e) {
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }

    /**
     * redis释放锁
     * @param key
     * @param clientId
     */
    public static void safeUnLock(String key, String clientId){
        try{
            String luaScript = "local resultVal = ARGV[1] local curr=redis.call('get', KEYS[1]) if resultVal == curr then redis.call('del', KEYS[1]) return 1 else return 0 end";
            RedisScript<Long> redisScript = RedisScript.of(luaScript,Long.class);
            Object execute = stringRedisTemplate.execute(redisScript, Collections.singletonList(key), Collections.singletonList(clientId));
            log.info("释放锁：{}",execute);
        }catch (Exception e){
            log.error("Redis出现异常:{}", e);
            throw new DubboBusinessException(DubboExceptionEnum.SYSTEM_ERROR);
       }
   }
}
