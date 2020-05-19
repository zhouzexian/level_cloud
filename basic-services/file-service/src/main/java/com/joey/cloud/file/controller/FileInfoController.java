package com.joey.cloud.file.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joey.cloud.common.core.constant.BaseConstant;
import com.joey.cloud.common.core.form.PageForm;
import com.joey.cloud.common.core.utils.DirUtil;
import com.joey.cloud.common.core.utils.PageUtil;
import com.joey.cloud.common.core.utils.ResponseUtil;
import com.joey.cloud.common.core.utils.SearchUtil;
import com.joey.cloud.common.core.vo.PageVo;
import com.joey.cloud.common.core.vo.ResponseVo;
import com.joey.cloud.file.entity.FileInfo;
import com.joey.cloud.file.service.IFileInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文件信息 前端控制器
 * </p>
 *
 * @author Joey
 * @since 2020-05-19
 */
@Slf4j
@RestController
@RequestMapping("/file-info")
public class FileInfoController {
    @Resource
    IFileInfoService fileInfoServiceImpl;
    @Value("${joey.file-path}")
    String baseFilePath;

    @ApiOperation(value = "获取列表-带分页")
    @GetMapping("/pageList")
    @ApiImplicitParam(name="searchJson",value="检索json",dataType = "string", paramType = "query",required = false)
    public ResponseVo<PageVo<FileInfo>> pageList(PageForm form, String searchJson){
        try {
            QueryWrapper qw = SearchUtil.parseWhereSql(searchJson);
            Page<FileInfo> page = fileInfoServiceImpl.page(PageUtil.getPage(form),qw);
            return ResponseVo.success(PageVo.create(page.getPages(),page.getTotal(),page.getRecords()));
        }catch (Exception e){
            log.error("获取列表-带分页出错,searchJson="+searchJson,e);
        }
        return ResponseVo.error();
    }

    @ApiOperation(value = "下载文件")
    @GetMapping("/download/{fileId}")
    @ApiImplicitParam(name = "fileId",value = "文件id",dataType = "long",paramType = "path", required = true)
    public void download(@PathVariable("fileId")Long fileId,
                         HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        try {
            FileInfo fileInfo = fileInfoServiceImpl.getById(fileId);
            if(fileInfo!=null){
                //String url= DirUtil.getRandomDir(fileInfo.getFileId())+fileInfo.getFileId()+fileInfo.getExtension();
                request.getRequestDispatcher(fileInfo.getFilePath()).forward(request, response);
            }
        }catch (Exception e){
            log.error("下载文件出错,fileId="+fileId,e);
            ResponseUtil.handle(response, ResponseVo.error());
        }
    }

    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "relationType",value = "关联类型1=主题2=题目3=选项4=主题类型5=测评结果6=用户反馈7=测试须知8=用户9=广告",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "relationKey",value = "业务关联id",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "files",value = "文件",dataType = "__file",paramType = "form",required = true)
    })
    public ResponseVo upload(HttpServletRequest request) {
        try {
            String relationKey = request.getParameter("relationKey");
            String relationType = request.getParameter("relationType");
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
            List<Long> fileIdList = null;
            if(files.size()>0){
                Date date = new Date();
                fileIdList = new ArrayList<>(files.size());
                for(MultipartFile file:files){
                    if (file.isEmpty()) {
                        continue;
                    }
                    long size = file.getSize();
                    String contentType = file.getContentType();
                    String fileName = file.getOriginalFilename();
                    String extension=fileName.substring(fileName.lastIndexOf("."),fileName.length());
                    FileInfo info = new FileInfo();
                    info.setContentType(contentType);
                    info.setExtension(extension);
                    info.setLength(size);
                    info.setFileName(fileName);
                    info.setCreateTime(date);
                    info.setRelationKey(relationKey);
                    if(StringUtils.isNotBlank(relationType)){
                        info.setRelationType(Integer.parseInt(relationType));
                    }
                    boolean save = fileInfoServiceImpl.save(info);
                    if(save){
                        try {
                            String path = DirUtil.getRandomDir(info.getFileId()) + info.getFileId()+extension;
                            File dest = new File(baseFilePath + path);
                            if (!dest.exists()) {
                                dest.getParentFile().mkdirs();
                                dest.createNewFile();
                            }
                            file.transferTo(dest);
                            fileIdList.add(info.getFileId());
                            info.setFilePath(path);
                            fileInfoServiceImpl.updateById(info);
                        }catch (Exception e){
                            log.error("文件上传失败,文件名称="+fileName,e);
                            fileInfoServiceImpl.removeById(info.getFileId());
                        }
                    }
                }
            }
            return ResponseVo.success(fileIdList);
        }catch (Exception e){
            log.error("上传文件出错",e);
        }
        return ResponseVo.error();
    }

    @ApiOperation(value = "删除文件")
    @DeleteMapping("/delete")
    @ApiImplicitParam(name = "ids",value = "文件id集合以英文逗号分隔",dataType = "string",paramType = "query", required = true)
    @ResponseBody
    public ResponseVo del(String ids){
        try {
            String[] arrSubId = ids.split(BaseConstant.SPITS_STR3);
            List<String> list = new ArrayList<>(arrSubId.length);
            Collections.addAll(list,arrSubId);
            for(String fileId:list){
                FileInfo fileInfo = fileInfoServiceImpl.getById(fileId);
                if(fileInfo!=null){
                    File file=new File(baseFilePath + fileInfo.getFilePath());
                    if(file.exists()){
                        file.delete();
                    }
                }
            }
            return ResponseVo.success(fileInfoServiceImpl.removeByIds(list));
        }catch (Exception e){
            log.error("删除文件出错,ids="+ids,e);
        }
        return ResponseVo.error();
    }

}

