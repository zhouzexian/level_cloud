package com.joey.cloud.psn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 访问权限
 * </p>
 *
 * @author Joey
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AuthorityUri对象", description="访问权限")
public class AuthorityUri implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "uri过滤")
    private String uri;

    @ApiModelProperty(value = "权限标识")
    @TableField("authorityKey")
    private String authorityKey;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "状态1=启用0=禁用")
    @TableField("statusId")
    private Integer statusId;


}
