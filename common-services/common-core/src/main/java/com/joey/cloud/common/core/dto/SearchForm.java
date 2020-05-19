package com.joey.cloud.common.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础搜索类
 * @author joey
 */
@Data
public class SearchForm implements Serializable {
    private static final long serialVersionUID = 330682664276072546L;
    /**
     * 数据库字段名
     */
    private String key;
    /**
     * 字段值
     */
    private String value;
    /**
     * 连接类型，如llike,equals,gt,ge,lt,le
     */
    private String type;
}
