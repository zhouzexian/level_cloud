package com.joey.cloud.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文件信息
 * </p>
 *
 * @author Joey
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FileInfo对象", description="文件信息")
public class FileInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "fileId", type = IdType.AUTO)
    private Long fileId;

    @ApiModelProperty(value = "文件名称")
    @TableField("fileName")
    private String fileName;

    @ApiModelProperty(value = "文件类型")
    @TableField("contentType")
    private String contentType;

    @ApiModelProperty(value = "扩展名")
    private String extension;

    @ApiModelProperty(value = "文件大小")
    private Long length;

    @ApiModelProperty(value = "文件路径")
    @TableField("filePath")
    private String filePath;

    @ApiModelProperty(value = "关联类型1=主题2=题目3=选项4=主题类型5=主题结果6=用户反馈7=测试须知8=用户9=广告")
    @TableField("relationType")
    private Integer relationType;

    @ApiModelProperty(value = "关联标识")
    @TableField("relationKey")
    private String relationKey;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;


}
