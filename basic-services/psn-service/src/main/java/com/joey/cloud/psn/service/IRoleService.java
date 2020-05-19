package com.joey.cloud.psn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joey.cloud.psn.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
public interface IRoleService extends IService<Role> {
    /**
     * 根据用户id获取角色key列表
     * @param psnId 用户id
     * @return 角色key列表
     */
    List<Role> findRoleListByPsnId(Long psnId);
}
