package com.joey.cloud.psn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joey.cloud.psn.entity.Authority;

import java.util.List;

/**
 * <p>
 * 权限信息表 服务类
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
public interface IAuthorityService extends IService<Authority> {
    /**
     * 通过角色id列表获取权限Key列表
     * @param roleIdList 角色id列表
     * @return 权限Key列表
     */
    List<String> findAuthorityKeyListByRoleIdList(List<Long> roleIdList);
}
