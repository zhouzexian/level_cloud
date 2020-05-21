package com.joey.cloud.common.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joey.cloud.common.core.constant.BaseConstant;
import com.joey.cloud.common.core.form.PageForm;
import com.joey.cloud.common.core.utils.PageUtil;
import com.joey.cloud.common.core.utils.SearchMybatisPlusUtil;
import com.joey.cloud.common.core.vo.PageVo;
import com.joey.cloud.common.core.vo.ResponseVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
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

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取所有记录")
    @GetMapping("")
    public ResponseVo<List<T>> getAll(){
        try {
            return ResponseVo.success(getService().list());
        }catch (Exception e){
            log.error("获取列表出错,searchJson=",e);
        }
        return ResponseVo.error();
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "插入记录" )
    @PostMapping("")
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

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取一条记录" )
    @GetMapping("/{id}")
    @ApiImplicitParam(name="id",value="id",dataType = "string", paramType = "path",required = true)
    public ResponseVo<T> get(@PathVariable String id){
        try {
            return ResponseVo.success(getService().getById(id));
        }catch (Exception e){
            log.error("获取信息出错,id="+id,e);
        }
        return ResponseVo.error();
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "更新记录" )
    @PutMapping("")
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

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除记录")
    @DeleteMapping("")
    @ApiImplicitParam(name="ids",value="id集合，以英文逗号分隔",dataType = "string", paramType = "query",required = true)
    public ResponseVo delete(String ids){
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

    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "分页检索记录")
    @GetMapping("/page")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchJson",value="检索json",dataType = "string", paramType = "query",required = false,defaultValue = "[{\"key\":\"\",\"value\":\"\",\"type\":\"eq\"}]")
    })
    public ResponseVo<PageVo<T>> pageList(PageForm form,String searchJson){
        try {
            QueryWrapper qw = SearchMybatisPlusUtil.parseWhereSql(searchJson);
            Page<T> page = getService().page(PageUtil.getPage(form),qw);
            return ResponseVo.success(PageVo.create(page.getPages(),page.getTotal(),page.getRecords()));
        }catch (Exception e){
            log.error("获取列表-带分页出错,searchJson="+searchJson,e);
        }
        return ResponseVo.error();
    }

}
