package com.joey.cloud.uaa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joey.cloud.common.core.constant.BaseConstant;
import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.common.core.vo.ResponseVo;
import com.joey.cloud.uaa.entity.Account;
import com.joey.cloud.uaa.service.IAccountService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Joey
 * @since 2020-05-19
 */
@Api(tags = "1、授权API")
@ApiSort(value = 2)
@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    IAccountService accountServiceImpl;

    @ApiOperation(value = "密码模式登陆")
    @GetMapping("/pwdLogin")
    @ApiImplicitParams({
            @ApiImplicitParam(name="accountNumber",value="账号",dataType = "string", paramType = "query",required = true),
            @ApiImplicitParam(name="password",value="密码",dataType = "string", paramType = "query",required = true)
    })
    public ResponseVo<AuthInfoDto> pwdLogin(@RequestParam("accountNumber") String accountNumber,
                               @RequestParam("password") String password){
        QueryWrapper qw = new QueryWrapper();
        qw.eq("accountNumber",accountNumber);
        Account account = accountServiceImpl.getOne(qw);
        if(account!=null&&password.equals(account.getPassword())){
            AuthInfoDto authInfo = accountServiceImpl.findAuthInfo(account.getPsnId());
            return ResponseVo.success(authInfo);
        }
        return ResponseVo.error(BaseConstant.ERROR_AUTH5);
    }

}

