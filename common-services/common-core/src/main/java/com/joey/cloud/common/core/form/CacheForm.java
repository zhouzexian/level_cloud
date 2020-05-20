package com.joey.cloud.common.core.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="缓存form")
public class CacheForm implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "缓存key")
    private String key;
    @ApiModelProperty(value = "缓存value")
    private String value;
    @ApiModelProperty(value = "缓存time")
    private Integer time;
}
