package com.joey.cloud.provider.cache.feign;

import com.joey.cloud.common.core.constant.ServiceNameConstants;
import com.joey.cloud.common.core.form.CacheForm;
import com.joey.cloud.provider.cache.fallback.CacheServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 缓存服务 feign
 * @author joey
 */
@FeignClient(name = ServiceNameConstants.cache,fallback = CacheServiceFallback.class)
public interface ICacheServiceFeign {

    /**
     * 获取缓存
     * @param key
     * @return
     */
    @RequestMapping(value = "/cache/get",method = RequestMethod.GET)
    String  get(@RequestParam("key") String key);

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    @RequestMapping(value = "/cache/set",method = RequestMethod.GET)
    Boolean set(@RequestParam("key") String key,@RequestParam("value") String value);

    /**
     * 写入缓存-分钟
     * @param key
     * @param value
     * @param time
     * @return
     */
    @RequestMapping(value = "/cache/setMinutes",method = RequestMethod.GET)
    Boolean  setMinutes(@RequestParam("key") String key,@RequestParam("value") String value,@RequestParam("time") Integer time);

    /**
     * 写入缓存-秒
     * @param key
     * @param value
     * @param time
     * @return
     */
    @RequestMapping(value = "/cache/setSeconds",method = RequestMethod.GET)
    Boolean  setSeconds(@RequestParam("key") String key,@RequestParam("value") String value,@RequestParam("time") Integer time);

    /**
     * 写入缓存-分钟-大内容时使用
     * @param form
     * @return
     */
    @RequestMapping(value = "/cache/setMinutes",method = RequestMethod.POST)
    Boolean setMinutes(@RequestBody CacheForm form);

}