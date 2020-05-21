package com.joey.cloud.common.core.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joey.cloud.common.core.dto.SearchForm;

import java.util.List;

/**
 * Mybatis搜索工具类
 * @author joey
 */
public class SearchMybatisPlusUtil {
    /**
     * 构造QueryWrapper
     * @param searchJson
     * @return
     */
    public static QueryWrapper parseWhereSql(String searchJson){
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StrUtil.isNotEmpty(searchJson)){
            List<SearchForm> searchList = JSON.parseArray(searchJson,SearchForm.class);
            if(CollUtil.isNotEmpty(searchList)){
                for(SearchForm form : searchList){
                    if(StrUtil.isEmpty(form.getType())){
                        queryWrapper.eq(form.getKey(),form.getValue());
                    }else {
                        switch (form.getType()){
                            case "eq": queryWrapper.eq(form.getKey(),form.getValue());break;
                            case "ne": queryWrapper.ne(form.getKey(),form.getValue());break;
                            case "like": queryWrapper.like(form.getKey(),form.getValue());break;
                            case "likeLeft": queryWrapper.likeLeft(form.getKey(),form.getValue());break;
                            case "likeRight": queryWrapper.likeRight(form.getKey(),form.getValue());break;
                            case "notLike": queryWrapper.notLike(form.getKey(),form.getValue());break;
                            case "gt": queryWrapper.gt(form.getKey(),form.getValue());break;
                            case "lt": queryWrapper.lt(form.getKey(),form.getValue());break;
                            case "ge": queryWrapper.ge(form.getKey(),form.getValue());break;
                            case "le": queryWrapper.le(form.getKey(),form.getValue());break;
                        }
                    }
                }
            }
        }
        return queryWrapper;
    }
}
