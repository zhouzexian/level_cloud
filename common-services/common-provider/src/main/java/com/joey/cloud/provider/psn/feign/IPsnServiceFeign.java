package com.joey.cloud.provider.psn.feign;

import com.joey.cloud.common.core.constant.ServiceNameConstants;
import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.common.core.dto.AuthorityUriDto;
import com.joey.cloud.common.core.vo.PageVo;
import com.joey.cloud.common.core.vo.ResponseVo;
import com.joey.cloud.provider.psn.fallback.PsnServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author joey
 */
@FeignClient(name = ServiceNameConstants.psn,fallback = PsnServiceFallback.class)
public interface IPsnServiceFeign {

    /**
     * 获取权限uri
     * @return 权限uri列表
     */
    @RequestMapping(value = "/authority-uri/findList",method = RequestMethod.GET)
    List<AuthorityUriDto> findAuthorityUriList();
    /**
     * 密码模式登陆
     * @param accountNumber 账号
     * @param password 密码
     * @return 用户id
     */
    @RequestMapping(value = "/account/pwdLogin",method = RequestMethod.GET)
    Long pwdLogin(@RequestParam("accountNumber") String accountNumber,
                           @RequestParam("password") String password);

    /**
     * 根据psnId 获取 用户信息
     * @param psnId 用户id
     * @return 用户信息
     */
    @RequestMapping(value = "/person/getAuthInfo",method = RequestMethod.GET)
    AuthInfoDto getAuthInfo(@RequestParam("psnId") Long psnId);


}