package com.joey.cloud.common.core.vo;

import com.joey.cloud.common.core.constant.BaseConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应对象封装
 * @author joey
 */
@Data
@ApiModel(value="请求响应ResponseVo")
public class ResponseVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "状态")
    private int status;
    @ApiModelProperty(value = "提示内容")
    private String message;
    @ApiModelProperty(value = "响应数据")
    private T data;

    public static ResponseVo noAuthority(String msg){
        ResponseVo dto = new ResponseVo();
        dto.setMessage(msg);
        dto.setStatus(BaseConstant.ERROR_403);
        return dto;
    }

    public static ResponseVo success(){
        ResponseVo dto = new ResponseVo();
        dto.setMessage(BaseConstant.SUCCESS_STR);
        dto.setStatus(BaseConstant.SUCCESS_200);
        return dto;
    }
    public static ResponseVo success(Object obj){
        ResponseVo dto = new ResponseVo();
        dto.setMessage(BaseConstant.SUCCESS_STR);
        dto.setStatus(BaseConstant.SUCCESS_200);
        dto.setData(obj);
        return dto;
    }
    public static ResponseVo error(){
        ResponseVo dto = new ResponseVo();
        dto.setMessage(BaseConstant.ERROR_STR);
        dto.setStatus(BaseConstant.ERROR_500);
        return dto;
    }
    public static ResponseVo error(Object obj){
        ResponseVo dto = new ResponseVo();
        dto.setMessage(BaseConstant.ERROR_STR);
        dto.setStatus(BaseConstant.ERROR_500);
        dto.setData(obj);
        return dto;
    }

}
