package com.joey.cloud.psn.controller;


import com.baomidou.mybatisplus.extension.service.IService;
import com.joey.cloud.common.core.controller.BaseController;
import com.joey.cloud.psn.entity.RelPersonRole;
import com.joey.cloud.psn.service.IRelPersonRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * <p>
 * 人员角色关系表 前端控制器
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
@Api(tags = "100、人员角色关系管理")
@ApiSort(value = 100)
@RestController
@RequestMapping("/rel-person-role")
@ApiIgnore
public class RelPersonRoleController extends BaseController<RelPersonRole> {
    @Resource
    IRelPersonRoleService relPersonRoleServiceImpl;

    @Override
    public IService<RelPersonRole> getService() {
        return relPersonRoleServiceImpl;
    }
}

