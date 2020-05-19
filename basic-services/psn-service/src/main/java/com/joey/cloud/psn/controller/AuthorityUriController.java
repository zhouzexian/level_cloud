package com.joey.cloud.psn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joey.cloud.common.core.controller.BaseController;
import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.common.core.dto.AuthorityUriDto;
import com.joey.cloud.psn.entity.AuthorityUri;
import com.joey.cloud.psn.service.IAuthorityUriService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 访问权限 前端控制器
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
@Api(tags = "5、权限控制 API")
@ApiSort(value = 5)
@RestController
@RequestMapping("/authority-uri")
public class AuthorityUriController extends BaseController<AuthorityUri> {
    @Resource
    IAuthorityUriService authorityUriServiceImpl;

    @ApiOperation(value = "获取权限uri列表")
    @GetMapping("/findList")
    List<AuthorityUriDto> findAuthorityUriList(){
        List<AuthorityUriDto> authorityUriDtoList= null;
        QueryWrapper<AuthorityUri> qw = new QueryWrapper();
        qw.select("uri","authorityKey");
        qw.eq("statusId",1);
        List<AuthorityUri> list = authorityUriServiceImpl.list(qw);
        if(list!=null&&list.size()>0){
            authorityUriDtoList = new ArrayList<>(list.size());
            for(AuthorityUri authorityUri:list){
                AuthorityUriDto dto = new AuthorityUriDto();
                dto.setUri(authorityUri.getUri());
                dto.setAuthorityKey(authorityUri.getAuthorityKey());
                authorityUriDtoList.add(dto);
            }
        }
        return authorityUriDtoList;
    }

    @Override
    public IService<AuthorityUri> getService() {
        return authorityUriServiceImpl;
    }
}

