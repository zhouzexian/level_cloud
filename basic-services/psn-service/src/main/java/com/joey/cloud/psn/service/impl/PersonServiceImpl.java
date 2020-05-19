package com.joey.cloud.psn.service.impl;

import com.joey.cloud.psn.entity.Person;
import com.joey.cloud.psn.mapper.PersonMapper;
import com.joey.cloud.psn.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人员信息表 服务实现类
 * </p>
 *
 * @author Joey
 * @since 2020-05-17
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}
