package com.joey.cloud.common.core.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author joey
 */
@Data
@ApiModel(value="搜索-es-form")
public class PageEsForm implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "当前页，从1开始")
    private Integer pageNo=1;
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize=10;

}
