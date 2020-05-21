package com.joey.cloud.es.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.joey.cloud.common.core.dto.SearchEsItemDto;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.List;

/**
 * Es查询工具类
 */
public class SearchEsUtil {
    /**
     * 构造 BoolQueryBuilder
     * @param searchJson
     * @return
     */
    public static BoolQueryBuilder getQuery(String searchJson){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(StrUtil.isNotEmpty(searchJson)){
            List<SearchEsItemDto> searchList = JSON.parseArray(searchJson, SearchEsItemDto.class);
            if(searchList!=null&&searchList.size()>0){
                for(SearchEsItemDto form:searchList){
                    if(StringUtils.isBlank(form.getKey())){
                        continue;
                    }
                    if(StringUtils.isBlank(form.getQueryType())){
                        boolQueryBuilder.must(build(form));
                    }else {
                        switch (form.getQueryType()){
                            case "must" :boolQueryBuilder.must(build(form));break;
                            case "should" :boolQueryBuilder.should(build(form));break;
                            case "filter" :boolQueryBuilder.filter(build(form));break;
                            case "mustNot" :boolQueryBuilder.mustNot(build(form));break;
                            default:
                        }
                    }
                }
            }
        }
        return boolQueryBuilder;

    }

    /**
     * 设置匹配模式 是否分词
     * @param form
     * @return
     */
    private static QueryBuilder build(SearchEsItemDto form){
        if(StringUtils.isNotBlank(form.getMatchType())){
            return QueryBuilders.matchQuery(form.getKey().toLowerCase(),form.getValue());
        }
        return QueryBuilders.termQuery(form.getKey().toLowerCase(),form.getValue());
    }
}
