package com.joey.cloud.uaa.controller;

import com.alibaba.fastjson.JSONObject;
import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.common.core.dto.AuthorityUriDto;
import com.joey.cloud.common.core.vo.ResponseVo;
import com.joey.cloud.provider.psn.feign.IPsnServiceFeign;
import com.joey.cloud.uaa.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author joey
 */
@Api(tags = "2、TOKEN API")
@ApiSort(value = 2)
@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {
    @Resource
    RedisUtil redisUtil;
    @Resource
    IPsnServiceFeign psnServiceFeign;
    @Value("${joey.tokenTimeOut}")
    Integer tokenTimeOut;
    String findUriListKey = "findUriList";

    @ApiOperation(value = "获取权限uri列表")
    @GetMapping("/findUriList")
    //@ApiIgnore
    public List<AuthorityUriDto> findUriList(){
        List<AuthorityUriDto> authorityUriList = null;
        String uriListJson = redisUtil.get(findUriListKey);
        if(StringUtils.isBlank(uriListJson)){
            authorityUriList = psnServiceFeign.findAuthorityUriList();
            redisUtil.setMinutes(findUriListKey,JSONObject.toJSONString(authorityUriList),30);
        }else {
            authorityUriList = JSONObject.parseObject(uriListJson, List.class);
        }
        return authorityUriList;
    }

    @ApiOperation(value = "解析token")
    @GetMapping("/check")
    @ApiImplicitParam(name="token",value="token",dataType = "string", paramType = "query",required = true)
    //@ApiIgnore
    public ResponseVo<AuthInfoDto> check(String token){
        try {
            String tokenJsonStr = redisUtil.get(token);
            if(StringUtils.isNotBlank(tokenJsonStr)){
                redisUtil.expire(token,tokenTimeOut, TimeUnit.MINUTES);
                return ResponseVo.success(JSONObject.parseObject(tokenJsonStr, AuthInfoDto.class));
            }
        }catch (Exception e){
            log.error("解析token出错",e);
        }
        return ResponseVo.error();
    }

    @ApiOperation(value = "注销token")
    @GetMapping("/logOff")
    @ApiImplicitParam(name="token",value="token",dataType = "string", paramType = "query",required = true)
    public ResponseVo<AuthInfoDto> logOff(String token){
        try {
            return ResponseVo.success(redisUtil.delete(token));
        }catch (Exception e){
            log.error("注销token出错",e);
        }
        return ResponseVo.error();
    }
}
