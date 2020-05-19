package com.joey.cloud.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author joey
 */
@Data
@ApiModel(value="用户信息")
public class PersonDto implements Serializable {
    private static final long serialVersionUID = -7655752360058745381L;

    @ApiModelProperty(value = "用户id")
    private Long psnId;

    @ApiModelProperty(value = "用户名称")
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
    private Date createDate;
}
