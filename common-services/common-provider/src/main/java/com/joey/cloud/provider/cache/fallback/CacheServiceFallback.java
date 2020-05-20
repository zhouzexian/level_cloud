package com.joey.cloud.provider.cache.fallback;

import com.joey.cloud.common.core.form.CacheForm;
import com.joey.cloud.provider.cache.feign.ICacheServiceFeign;
import org.springframework.stereotype.Component;

/**
 * psn服务feign fallback
 * @author joey
 */
@Component
public class CacheServiceFallback implements ICacheServiceFeign {

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public Boolean set(String key, String value) {
        return null;
    }

    @Override
    public Boolean setMinutes(String key, String value, Integer time) {
        return null;
    }

    @Override
    public Boolean setSeconds(String key, String value, Integer time) {
        return null;
    }

    @Override
    public Boolean setMinutes(CacheForm form) {
        return null;
    }


}