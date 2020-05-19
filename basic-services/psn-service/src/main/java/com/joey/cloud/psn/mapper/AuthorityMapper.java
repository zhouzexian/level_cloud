package com.joey.cloud.psn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joey.cloud.psn.entity.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限信息表 Mapper 接口
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
public interface AuthorityMapper extends BaseMapper<Authority> {


    List<String> findAuthorityKeyListByRoleIdList(@Param("params") Map<String, Object> params);
}
