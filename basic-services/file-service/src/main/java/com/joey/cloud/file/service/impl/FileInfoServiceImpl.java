package com.joey.cloud.file.service.impl;

import com.joey.cloud.file.entity.FileInfo;
import com.joey.cloud.file.mapper.FileInfoMapper;
import com.joey.cloud.file.service.IFileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件信息 服务实现类
 * </p>
 *
 * @author Joey
 * @since 2020-05-19
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements IFileInfoService {

}
