package com.joey.cloud.es.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author joey
 */
@ApiModel(value="测试博客")
@Data
@Accessors(chain = true)
@Document(indexName = "blog", type = "java")
public class BlogModel implements Serializable {
    private static final long serialVersionUID = 6320548148250372657L;

    @ApiModelProperty(value = "主键")
    @Id
    private String id;
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * @DateTimeFormat(pattern = "yyyy-MM-dd")
     * @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
     */
    @ApiModelProperty(value = "时间")
    @Field(type = FieldType.Date, format = DateFormat.basic_date)
    private Date time;
}