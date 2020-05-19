package com.joey.cloud.uaa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Joey
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Account对象", description="")
public class Account implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "accountId", type = IdType.AUTO)
    private Long accountId;

    @ApiModelProperty(value = "账号")
    @TableField("accountNumber")
    private String accountNumber;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "创建时间")
    @TableField("createDate")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "状态1=正常，0=禁用")
    @TableField("statusId")
    private Integer statusId;

    @ApiModelProperty(value = "人员id")
    @TableField("psnId")
    private Long psnId;


}
