package com.joey.cloud.flowable.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流程控制器
 * @author joey
 */
@Api(tags = "1、流程 API")
@ApiSort(value = 2)
@Slf4j
@RestController
@RequestMapping("/flowable")
public class FlowableController {

}
