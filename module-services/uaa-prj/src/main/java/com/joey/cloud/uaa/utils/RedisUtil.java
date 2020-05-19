package com.joey.cloud.uaa.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 * @author joey
 */
@Slf4j
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存出错",e);
        }
        return result;
    }

    /**
     * 写入缓存
     */
    public boolean setSeconds(final String key, String value,int time) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value,time, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存出错",e);
        }
        return result;
    }

    /**
     * 写入缓存
     */
    public boolean setMinutes(final String key, String value,int time) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value,time, TimeUnit.MINUTES);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存出错",e);
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            log.error("更新缓存出错",e);
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            log.error("删除缓存出错",e);
        }
        return result;
    }

    /**
     * 判断是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

}
