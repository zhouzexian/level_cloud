package com.joey.cloud.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author joey
 */
@Data
@ApiModel(value="搜索-es-form")
public class SearchEsForm implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "当前页，从1开始")
    private Integer pageNo=1;
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize=10;
    @ApiModelProperty(value = "检索项列表")
    List<SearchEsItem> searchList;

}
