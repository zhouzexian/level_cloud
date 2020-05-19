package com.joey.cloud.psn.service.impl;

import com.google.common.collect.Maps;
import com.joey.cloud.psn.entity.Role;
import com.joey.cloud.psn.mapper.RoleMapper;
import com.joey.cloud.psn.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    RoleMapper roleMapper;

    @Override
    public List<Role> findRoleListByPsnId(Long psnId) {
        Map<String,Object> params = new HashMap<>(4);
        params.put("psnId",psnId);
        return roleMapper.findRoleListByPsnId(params);
    }
}
