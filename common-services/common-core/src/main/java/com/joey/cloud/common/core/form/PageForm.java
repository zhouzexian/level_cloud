package com.joey.cloud.common.core.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 分页form
 * @author joey
 */
@Data
@ApiModel(value="分页form")
public class PageForm implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "当前页，从1开始")
    private Long pageNo=1L;
    @ApiModelProperty(value = "每页数量")
    private Long pageSize=10L;
}
