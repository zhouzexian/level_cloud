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
import java.time.LocalDateTime;

/**
 * <p>
 * 人员信息表
 * </p>
 *
 * @author Joey
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Person对象", description="人员信息表")
public class Person implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "人员标识")
    @TableId(value = "psnId", type = IdType.AUTO)
    private Long psnId;

    @ApiModelProperty(value = "人员名称")
    @TableField("psnName")
    private String psnName;

    @ApiModelProperty(value = "性别0=未知1=男2=女")
    private Integer sex;

    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "电子邮件")
    private String email;

    @ApiModelProperty(value = "qq号码")
    private String qq;

    @ApiModelProperty(value = "微信号码")
    private String wechat;

    @ApiModelProperty(value = "创建时间")
    @TableField("createDate")
    private LocalDateTime createDate;


}
