package com.joey.cloud.es.utils;

import com.joey.cloud.common.core.dto.SearchEsForm;
import com.joey.cloud.common.core.vo.PageVo;
import com.joey.cloud.common.core.vo.ResponseVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "检索列表")
    @PostMapping("/pageSearch")
    public ResponseVo<PageVo<T>> pageSearch(@RequestBody SearchEsForm form) {
        Pageable pageable = PageableUtil.getPageable(form);
        BoolQueryBuilder query = SearchEsUtil.getQuery(form.getSearchList());
        Page<T> page = getRepository().search(query, pageable);
        return ResponseVo.success(PageVo.create(page.getTotalPages(),page.getTotalElements(),page.getContent()));
    }

    @ApiOperation(value = "插入信息")
    @PostMapping("/insert")
    public T insert(@RequestBody T t) {
        return getRepository().save(t);
    }

    @ApiOperation(value = "获取信息")
    @GetMapping("/get/{id}")
    @ApiImplicitParam(name="id",value="id",dataType = "string", paramType = "path",required = true)
    public T get(@PathVariable V id) {
        Optional<T> blogModelOptional = getRepository().findById(id);
        if (blogModelOptional.isPresent()) {
            return blogModelOptional.get();
        }
        return null;
    }

    @ApiOperation(value = "获取所有")
    @GetMapping("/getAll")
    public List<T> getAll() {
        Iterable<T> iterable = getRepository().findAll();
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    @ApiOperation(value = "更新信息")
    @PostMapping("/update")
    public T updateById(@RequestBody T t) {
        return getRepository().save(t);
    }

    @ApiOperation(value = "删除一条信息")
    @DeleteMapping("/delete/{id}")
    @ApiImplicitParam(name="id",value="id",dataType = "string", paramType = "path",required = true)
    public void deleteById(@PathVariable V id) {
        getRepository().deleteById(id);
    }

    @ApiOperation(value = "删除所有信息")
    @DeleteMapping("/deleteAll")
    public void deleteById() {
        getRepository().deleteAll();
    }

}
