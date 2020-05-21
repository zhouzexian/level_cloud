package com.joey.cloud.es.utils;

import com.joey.cloud.common.core.form.PageEsForm;
import com.joey.cloud.common.core.vo.PageVo;
import com.joey.cloud.common.core.vo.ResponseVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * es基本操作封装
 * @author joey
 */
@Slf4j
public abstract class EsBaseController<T,V> {
    /**
     * 获取操作Repository
     * @return
     */
    public abstract ElasticsearchRepository<T,V> getRepository();

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取所有记录")
    @GetMapping("")
    public List<T> getAll() {
        Iterable<T> iterable = getRepository().findAll();
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "插入记录")
    @PostMapping("")
    public T insert(@RequestBody T t) {
        return getRepository().save(t);
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取一条记录")
    @GetMapping("/{id}")
    @ApiImplicitParam(name="id",value="id",dataType = "string", paramType = "path",required = true)
    public T get(@PathVariable V id) {
        Optional<T> blogModelOptional = getRepository().findById(id);
        if (blogModelOptional.isPresent()) {
            return blogModelOptional.get();
        }
        return null;
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "更新记录")
    @PutMapping("")
    public T updateById(@RequestBody T t) {
        return getRepository().save(t);
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除一条记录")
    @DeleteMapping("/{id}")
    @ApiImplicitParam(name="id",value="id",dataType = "string", paramType = "path",required = true)
    public void deleteById(@PathVariable V id) {
        getRepository().deleteById(id);
    }

    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除所有记录")
    @DeleteMapping("")
    public void deleteById() {
        getRepository().deleteAll();
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "分页检索记录")
    @GetMapping("/page")
    @ApiImplicitParams({
        @ApiImplicitParam(name="searchJson",value="检索json",dataType = "string", paramType = "query",required = false,defaultValue = "[{\"key\":\"\",\"value\":\"\",\"queryType\":\"must\",\"matchType\":\"\"}]")
    })
    public ResponseVo<PageVo<T>> pageSearch(PageEsForm form, String searchJson) {
        Pageable pageable = PageableUtil.getPageable(form);
        BoolQueryBuilder query = SearchEsUtil.getQuery(searchJson);
        Page<T> page = getRepository().search(query, pageable);
        return ResponseVo.success(PageVo.create(page.getTotalPages(),page.getTotalElements(),page.getContent()));
    }

}
