package com.joey.cloud.es.service;

import com.joey.cloud.es.entity.BlogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author joey
 */
public interface BlogRepository extends ElasticsearchRepository<BlogModel, String> {
}
