package com.joey.cloud.common.core.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * jwt封装类
 * @author joey
 */
@Data
public class JwtDto {
    private String jti;
    private String client_id;
    private String psnId;
    private Long exp;
    private List<String> aud;
    private List<String> scope;
    private List<String> roleKeyList;
    private List<String> authorityKeyList;
}
