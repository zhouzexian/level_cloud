package com.joey.cloud.uaa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.common.core.utils.DesUtil;
import com.joey.cloud.provider.psn.feign.IPsnServiceFeign;
import com.joey.cloud.uaa.entity.Account;
import com.joey.cloud.uaa.mapper.AccountMapper;
import com.joey.cloud.uaa.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joey.cloud.uaa.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Joey
 * @since 2020-05-19
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Resource
    RedisUtil redisUtil;
    @Resource
    IPsnServiceFeign psnServiceFeign;
    @Value("${joey.tokenTimeOut}")
    Integer tokenTimeOut;

    @Override
    public AuthInfoDto findAuthInfo(Long psnId) {
        try {
            AuthInfoDto authInfo = getAuthInfoDto(psnId, psnServiceFeign, redisUtil, tokenTimeOut);
            if (authInfo != null){
                return authInfo;
            };
        }catch (Exception e){
            log.error("获取用户授权信息出错",e);
        }
        return null;
    }

    public static AuthInfoDto getAuthInfoDto(Long psnId, IPsnServiceFeign psnServiceFeign, RedisUtil redisUtil, Integer tokenTimeOut) {
        AuthInfoDto authInfo = psnServiceFeign.getAuthInfo(psnId);
        if(authInfo!=null){
            String token = DesUtil.getEncryptString(psnId.toString() + System.currentTimeMillis()+ IdWorker.get32UUID());
            authInfo.setAccessToken(token);
            redisUtil.setMinutes(token, JSONObject.toJSONString(authInfo), tokenTimeOut);
            return authInfo;
        }
        return null;
    }
}
