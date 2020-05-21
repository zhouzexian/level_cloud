package com.joey.cloud.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * [{"key":"","value":"","queryType":"must","matchType":""}]
 * es检索封装
 * @author joey
 */
@Data
@ApiModel(value="搜索-es-item")
public class SearchEsItemDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "字段名")
    private String key;
    @ApiModelProperty(value = "字段值")
    private String value;
    @ApiModelProperty(value = "查询类型，如must，filter，should，mustNot")
    private String queryType;
    @ApiModelProperty(value = "匹配类型，空=直接匹配1=分词处理")
    private String matchType;

}
