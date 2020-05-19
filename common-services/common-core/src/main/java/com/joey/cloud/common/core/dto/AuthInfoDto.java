package com.joey.cloud.common.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * oauth 认证传送信息实体
 * @author joey
 */
@Data
@ApiModel(value="用户授权信息")
public class AuthInfoDto implements Serializable {
    private static final long serialVersionUID = 330682664276072546L;

    @ApiModelProperty(value = "用户信息")
    private PersonDto personInfo;

    @ApiModelProperty(value = "角色key列表")
    private List<String> roleKeyList;

    @ApiModelProperty(value = "权限key列表")
    private List<String> authorityKeyList;

    @ApiModelProperty(value = "认证token")
    private String accessToken;
}
