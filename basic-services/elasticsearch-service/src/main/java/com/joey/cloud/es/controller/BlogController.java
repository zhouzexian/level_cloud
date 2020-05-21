package com.joey.cloud.es.controller;

import com.joey.cloud.es.entity.BlogModel;
import com.joey.cloud.es.service.BlogRepository;
import com.joey.cloud.es.utils.EsBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author joey
 */
@Api(tags = "1、测试 API")
@ApiSort(value = 1)
@RestController
@RequestMapping("/blog")
public class BlogController extends EsBaseController<BlogModel,String> {
    @Resource
    private BlogRepository blogRepository;


    @Override
    public ElasticsearchRepository<BlogModel, String> getRepository() {
        return blogRepository;
    }
}
