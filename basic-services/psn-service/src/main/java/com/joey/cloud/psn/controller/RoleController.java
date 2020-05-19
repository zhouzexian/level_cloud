package com.joey.cloud.psn.controller;


import com.baomidou.mybatisplus.extension.service.IService;
import com.joey.cloud.common.core.controller.BaseController;
import com.joey.cloud.psn.entity.Role;
import com.joey.cloud.psn.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
@Api(tags = "3、角色 API")
@ApiSort(value = 3)
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<Role> {
    @Resource
    IRoleService roleServiceImpl;

    @Override
    public IService<Role> getService() {
        return roleServiceImpl;
    }
}

