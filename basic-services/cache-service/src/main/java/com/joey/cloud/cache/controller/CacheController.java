package com.joey.cloud.cache.controller;

import com.joey.cloud.cache.utils.RedisUtil;
import com.joey.cloud.common.core.form.CacheForm;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 缓存控制器
 * @author joey
 */
@Api(tags = "1、缓存 API")
@ApiSort(value = 1)
@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheController {
    @Resource
    RedisUtil redisUtil;


    @ApiOperation(value = "获取缓存")
    @GetMapping("/get")
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="缓存key",dataType = "string", paramType = "query",required = true)
    })
    public String get(String key){
        return redisUtil.get(key);
    }


    @ApiOperation(value = "写入缓存")
    @GetMapping("/set")
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="缓存key",dataType = "string", paramType = "query",required = true),
            @ApiImplicitParam(name="value",value="缓存value",dataType = "string", paramType = "query",required = true)
    })
    public boolean set(String key,String value){
        return  redisUtil.set(key,value);
    }


    @ApiOperation(value = "写入缓存-分钟")
    @GetMapping("/setMinutes")
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="缓存key",dataType = "string", paramType = "query",required = true),
            @ApiImplicitParam(name="value",value="缓存value",dataType = "string", paramType = "query",required = true),
            @ApiImplicitParam(name="time",value="缓存时间",dataType = "int", paramType = "query",required = true)
    })
    public boolean setMinutes(String key,String value,Integer time){
        return  redisUtil.setMinutes(key,value,time);
    }

    @ApiOperation(value = "写入缓存-秒")
    @GetMapping("/setSeconds")
    @ApiImplicitParams({
            @ApiImplicitParam(name="key",value="缓存key",dataType = "string", paramType = "query",required = true),
            @ApiImplicitParam(name="value",value="缓存value",dataType = "string", paramType = "query",required = true),
            @ApiImplicitParam(name="time",value="缓存时间",dataType = "int", paramType = "query",required = true)
    })
    public boolean setSeconds(String key,String value,Integer time){
        return redisUtil.setSeconds(key,value,time);
    }

    @ApiOperation(value = "写入缓存-分钟")
    @PostMapping("/setMinutes")
    public boolean setMinutes(@RequestBody CacheForm form){
        return redisUtil.setMinutes(form.getKey(),form.getValue(),form.getTime());
    }


}
