package com.joey.cloud.common.core.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author joey
 */
@Data
@ApiModel(value="邮件form")
public class MailForm implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "收件人mail")
    private String addressee;
    @ApiModelProperty(value = "标题")
    private String subject;
    @ApiModelProperty(value = "邮件内容")
    private String text;
    @ApiModelProperty(value = "附件路径")
    private String filePath;




}
