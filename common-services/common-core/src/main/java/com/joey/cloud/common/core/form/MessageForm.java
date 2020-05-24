package com.joey.cloud.common.core.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author joey
 */
@Data
@ApiModel(value="消息form")
public class MessageForm implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "消息id")
    private String messageId;
    @ApiModelProperty(value = "消息类型")
    private String messageType;
    @ApiModelProperty(value = "消息数据")
    private String messageData;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
