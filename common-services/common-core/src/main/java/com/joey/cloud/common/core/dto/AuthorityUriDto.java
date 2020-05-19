package com.joey.cloud.common.core.dto;

import lombok.Data;

/**
 * 权限uri
 * @author joey
 */
@Data
public class AuthorityUriDto {
    /**
     * uri过滤
     */
    private String uri;

    /**
     * 权限标识
     */
    private String authorityKey;
}
