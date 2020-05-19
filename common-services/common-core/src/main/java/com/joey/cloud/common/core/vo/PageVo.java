package com.joey.cloud.common.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果对象封装
 * @author joey
 */
@Data
@ApiModel(value="分页PageVo")
public class PageVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "总页数")
    private Long pages;
    @ApiModelProperty(value = "总数量")
    private Long total;
    @ApiModelProperty(value = "列表对象")
    private List<T> list;

    public static PageVo create(Long pages, Long total,List list){
        PageVo vo = new PageVo();
        vo.setList(list);
        vo.setPages(pages);
        vo.setTotal(total);
        return vo;
    }


}
