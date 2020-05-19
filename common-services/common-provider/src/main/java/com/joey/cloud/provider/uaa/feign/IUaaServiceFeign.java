package com.joey.cloud.provider.uaa.feign;

import com.joey.cloud.common.core.constant.ServiceNameConstants;
import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.common.core.dto.AuthorityUriDto;
import com.joey.cloud.common.core.vo.ResponseVo;
import com.joey.cloud.provider.uaa.fallback.IUaaServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author joey
 */
@FeignClient(name = ServiceNameConstants.uaa,fallback = IUaaServiceFallback.class)
public interface IUaaServiceFeign {

    /**
     * 获取授权列表
     * @return
     */
    @RequestMapping(value = "/token/findUriList",method = RequestMethod.GET)
    List<AuthorityUriDto> findUriList();

    /**
     * 检查token是否有效
     * @param token
     * @return
     */
    @RequestMapping(value = "/token/check",method = RequestMethod.GET)
    ResponseVo<AuthInfoDto> check(@RequestParam("token") String token);
}
