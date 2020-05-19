package com.joey.cloud.common.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joey.cloud.common.core.constant.BaseConstant;
import com.joey.cloud.common.core.form.PageForm;
import com.joey.cloud.common.core.utils.PageUtil;
import com.joey.cloud.common.core.utils.SearchUtil;
import com.joey.cloud.common.core.vo.PageVo;
import com.joey.cloud.common.core.vo.ResponseVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 基本操作封装
 * @author joey
 */
@Slf4j
public abstract class BaseController<T> {
    /**
     * 获取操作Service
     * @return
     */
    public abstract IService<T> getService();

    @ApiOperation(value = "获取列表")
    @GetMapping("/list")
    @ApiImplicitParam(name="searchJson",value="检索json",dataType = "string", paramType = "query",required = false)
    public ResponseVo<List<T>> list(String searchJson){
        try {
            QueryWrapper qw = SearchUtil.parseWhereSql(searchJson);
            return ResponseVo.success(getService().list(qw));
        }catch (Exception e){
            log.error("获取列表出错,searchJson="+searchJson,e);
        }
        return ResponseVo.error();
    }

    @ApiOperation(value = "获取列表-带分页")
    @GetMapping("/pageList")
    @ApiImplicitParam(name="searchJson",value="检索json",dataType = "string", paramType = "query",required = false)
    public ResponseVo<PageVo<T>> pageList(PageForm form,String searchJson){
        try {
            QueryWrapper qw = SearchUtil.parseWhereSql(searchJson);
            Page<T> page = getService().page(PageUtil.getPage(form),qw);
            return ResponseVo.success(PageVo.create(page.getPages(),page.getTotal(),page.getRecords()));
        }catch (Exception e){
            log.error("获取列表-带分页出错,searchJson="+searchJson,e);
        }
        return ResponseVo.error();
    }

    @ApiOperation(value = "获取实体" )
    @GetMapping("/get/{id}")
    @ApiImplicitParam(name="id",value="id",dataType = "string", paramType = "path",required = true)
    public ResponseVo<T> get(@PathVariable String id){
        try {
            return ResponseVo.success(getService().getById(id));
        }catch (Exception e){
            log.error("获取信息出错,id="+id,e);
        }
        return ResponseVo.error();
    }

    @ApiOperation(value = "插入信息" )
    @PostMapping("/insert")
    public ResponseVo<T> insert(@RequestBody T t){
        try {
            boolean save = getService().save(t);
            if(save){
                return ResponseVo.success(t);
            }
        }catch (Exception e){
            log.error("插入出错",e);
        }
        return ResponseVo.error();
    }
    @ApiOperation(value = "更新信息" )
    @PostMapping("/update")
    public ResponseVo<T> update(@RequestBody T t){
        try {
            boolean save = getService().updateById(t);
            if(save){
                return ResponseVo.success(t);
            }
        }catch (Exception e){
            log.error("更新出错",e);
        }
        return ResponseVo.error();
    }
    @ApiOperation(value = "删除信息")
    @DeleteMapping("/delete")
    @ApiImplicitParam(name="ids",value="id集合，以英文逗号分隔",dataType = "string", paramType = "query",required = true)
    public ResponseVo del(String ids){
        try {
            if(StringUtils.isNotBlank(ids)){
                String[] arrId = ids.split(BaseConstant.SPITS_STR3);
                List<String> list = new ArrayList<>(arrId.length);
                Collections.addAll(list,arrId);
                return ResponseVo.success(getService().removeByIds(list));
            }
        }catch (Exception e){
            log.error("删除信息出错,ids="+ids,e);
        }
        return ResponseVo.error();
    }

}
