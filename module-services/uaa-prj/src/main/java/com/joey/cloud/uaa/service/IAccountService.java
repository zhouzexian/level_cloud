package com.joey.cloud.uaa.service;

import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.uaa.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Joey
 * @since 2020-05-19
 */
public interface IAccountService extends IService<Account> {
    /**
     * 获取用户授权信息
     * @param psnId
     * @return
     */
    AuthInfoDto findAuthInfo(Long psnId);

}
