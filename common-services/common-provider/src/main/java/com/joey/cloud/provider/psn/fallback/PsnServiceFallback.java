package com.joey.cloud.provider.psn.fallback;

import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.common.core.dto.AuthorityUriDto;
import com.joey.cloud.provider.psn.feign.IPsnServiceFeign;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * psn服务feign fallback
 * @author joey
 */
@Component
public class PsnServiceFallback implements IPsnServiceFeign {

    @Override
    public List<AuthorityUriDto> findAuthorityUriList() {
        return null;
    }

    @Override
    public Long pwdLogin(String accountNumber, String password) {
        return null;
    }

    @Override
    public AuthInfoDto getAuthInfo(Long psnId) {
        return null;
    }
}