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
 * 权限信息表
 * </p>
 *
 * @author Joey
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Authority对象", description="权限信息表")
public class Authority implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "权限id")
    @TableId(value = "authorityId", type = IdType.AUTO)
    private Integer authorityId;

    @ApiModelProperty(value = "权限名")
    @TableField("authorityName")
    private String authorityName;

    @ApiModelProperty(value = "权限key")
    @TableField("authorityKey")
    private String authorityKey;


}
