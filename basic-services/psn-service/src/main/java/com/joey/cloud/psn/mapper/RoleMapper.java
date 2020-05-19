package com.joey.cloud.psn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joey.cloud.psn.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过人员获取角色列表
     * @param params
     * @return
     */
    List<Role> findRoleListByPsnId(@Param("params") Map<String, Object> params);
}
