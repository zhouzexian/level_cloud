package com.joey.cloud.provider.uaa.fallback;

import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.common.core.dto.AuthorityUriDto;
import com.joey.cloud.common.core.vo.ResponseVo;
import com.joey.cloud.provider.uaa.feign.IUaaServiceFeign;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author joey
 */
@Component
public class IUaaServiceFallback implements IUaaServiceFeign {


    @Override
    public List<AuthorityUriDto> findUriList() {
        return null;
    }

    @Override
    public ResponseVo<AuthInfoDto> check(String token) {
        return null;
    }
}
