package com.joey.cloud.psn.controller;


import com.baomidou.mybatisplus.extension.service.IService;
import com.joey.cloud.common.core.controller.BaseController;
import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.common.core.dto.PersonDto;
import com.joey.cloud.psn.entity.Person;
import com.joey.cloud.psn.entity.Role;
import com.joey.cloud.psn.service.IAuthorityService;
import com.joey.cloud.psn.service.IPersonService;
import com.joey.cloud.psn.service.IRoleService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 人员信息表 前端控制器
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
@Api(tags = "2、用户 API")
@ApiSort(value = 2)
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController<Person> {
    public final static BeanCopier personCopier =
            BeanCopier.create(Person.class, PersonDto.class, false);
    @Resource
    IPersonService personServiceImpl;
    @Resource
    IRoleService roleServiceImpl;
    @Resource
    IAuthorityService authorityServiceImpl;

    @ApiOperation(value = "通过psnId获取人员授权信息")
    @GetMapping("/getAuthInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="psnId",value="用户id",dataType = "long", paramType = "query",required = true)
    })
    public AuthInfoDto getAuthInfo(@RequestParam("psnId") Long psnId){
        AuthInfoDto dto  = null;
        try {
            //1、获取人员信息
            Person person = personServiceImpl.getById(psnId);
            if(person!=null){
                dto  = new AuthInfoDto();
                PersonDto psnDto = new PersonDto();
                personCopier.copy(person,psnDto,null);
                dto.setPersonInfo(psnDto);
                //2、获取角色信息
                List<Role> roleList = roleServiceImpl.findRoleListByPsnId(psnId);
                if(roleList!=null&&roleList.size()>0){
                    List<Long> roleIdList = new ArrayList<>(roleList.size());
                    List<String> roleKeyList = new ArrayList<>(roleList.size());
                    for(Role role:roleList){
                        roleIdList.add(role.getRoleId());
                        roleKeyList.add(role.getRoleKey());
                    }
                    dto.setRoleKeyList(roleKeyList);
                    //3、获取权限信息
                    List<String> authorityKeyList = authorityServiceImpl.findAuthorityKeyListByRoleIdList(roleIdList);
                    dto.setAuthorityKeyList(authorityKeyList);
                }
            }
        }catch (Exception e){
            log.error("通过psnId获取人员授权信息出错",e);
        }
        return dto;
    }

    @Override
    public IService<Person> getService() {
        return personServiceImpl;
    }
}

