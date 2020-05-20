package com.joey.cloud.es.controller;

import com.joey.cloud.es.entity.BlogModel;
import com.joey.cloud.es.service.BlogRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author joey
 */
@Api(tags = "1、测试 API")
@ApiSort(value = 4)
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogRepository blogRepository;

    @PostMapping("/add")
    public BlogModel add(@RequestBody BlogModel blogModel) {
        return blogRepository.save(blogModel);
    }

    @GetMapping("/get/{id}")
    public BlogModel getById(@PathVariable String id) {
        Optional<BlogModel> blogModelOptional = blogRepository.findById(id);
        if (blogModelOptional.isPresent()) {
            return blogModelOptional.get();
        }
        return null;
    }

    @GetMapping("/get")
    public List<BlogModel> getAll() {
        Iterable<BlogModel> iterable = blogRepository.findAll();
        List<BlogModel> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    @PostMapping("/update")
    public BlogModel updateById(@RequestBody BlogModel blogModel) {
        return blogRepository.save(blogModel);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        blogRepository.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteById() {
        blogRepository.deleteAll();
    }

}
