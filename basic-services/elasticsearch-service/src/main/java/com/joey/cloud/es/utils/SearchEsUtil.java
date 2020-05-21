package com.joey.cloud.es.utils;

import com.joey.cloud.common.core.dto.SearchEsItem;
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
     * @param searchList
     * @return
     */
    public static BoolQueryBuilder getQuery(List<SearchEsItem> searchList){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(searchList!=null&&searchList.size()>0){
            for(SearchEsItem form:searchList){
                if(StringUtils.isBlank(form.getKey())){
                    continue;
                }
                switch (form.getQueryType()){
                    case "must" :
                        boolQueryBuilder.must(build(form));
                        break;
                    case "should" :
                        boolQueryBuilder.should(build(form));
                        break;
                    case "filter" :
                        boolQueryBuilder.filter(build(form));
                        break;
                    case "mustNot" :
                        boolQueryBuilder.mustNot(build(form));
                        break;
                    default:
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
    private static QueryBuilder build(SearchEsItem form){
        if(StringUtils.isNotBlank(form.getMatchType())){
            return QueryBuilders.matchQuery(form.getKey().toLowerCase(),form.getValue());
        }
        return QueryBuilders.termQuery(form.getKey().toLowerCase(),form.getValue());
    }
}
