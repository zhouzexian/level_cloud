package com.joey.cloud.psn.controller;


import com.baomidou.mybatisplus.extension.service.IService;
import com.joey.cloud.common.core.controller.BaseController;
import com.joey.cloud.psn.entity.Authority;
import com.joey.cloud.psn.service.IAuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 权限信息表 前端控制器
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
@Api(tags = "4、权限 API")
@ApiSort(value = 4)
@RestController
@RequestMapping("/authority")
public class AuthorityController extends BaseController<Authority> {
    @Resource
    IAuthorityService authorityServiceImpl;

    @Override
    public IService<Authority> getService() {
        return authorityServiceImpl;
    }
}

