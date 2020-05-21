package com.joey.cloud.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础搜索类
 * @author joey
 */
@Data
@ApiModel(value="搜索-mybatis-form")
public class SearchForm implements Serializable {
    private static final long serialVersionUID = 330682664276072546L;
    @ApiModelProperty(value = "数据库字段名")
    private String key;
    @ApiModelProperty(value = "数据库字段值")
    private String value;
    @ApiModelProperty(value = "连接类型，如eq、like、likeRight、likeLeft、notLike、gt、lt、ne、le")
    private String type;
}
