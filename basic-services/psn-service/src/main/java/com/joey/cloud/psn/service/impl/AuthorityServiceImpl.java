package com.joey.cloud.psn.service.impl;

import com.joey.cloud.psn.entity.Authority;
import com.joey.cloud.psn.mapper.AuthorityMapper;
import com.joey.cloud.psn.service.IAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限信息表 服务实现类
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements IAuthorityService {
    @Resource
    AuthorityMapper authorityMapper;


    @Override
    public List<String> findAuthorityKeyListByRoleIdList(List<Long> roleIdList) {
        Map<String,Object> params = new HashMap<>(4);
        params.put("roleIdList",roleIdList);
        return authorityMapper.findAuthorityKeyListByRoleIdList(params);
    }
}
