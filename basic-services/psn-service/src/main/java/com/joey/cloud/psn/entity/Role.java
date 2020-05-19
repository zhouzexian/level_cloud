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
 * 角色信息表
 * </p>
 *
 * @author Joey
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Role对象", description="角色信息表")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色标识")
    @TableId(value = "roleId", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    @TableField("roleName")
    private String roleName;

    @ApiModelProperty(value = "角色key")
    @TableField("roleKey")
    private String roleKey;

    @ApiModelProperty(value = "角色类型")
    @TableField("roleType")
    private String roleType;

    @ApiModelProperty(value = "父级id")
    @TableField("parentId")
    private Long parentId;

    @ApiModelProperty(value = "状态1=启用0=禁用")
    @TableField("statusId")
    private Integer statusId;


}
