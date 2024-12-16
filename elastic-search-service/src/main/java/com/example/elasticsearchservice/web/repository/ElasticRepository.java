package com.example.elasticsearchservice.web.repository;


import com.example.elasticsearchservice.web.entity.Posts;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRepository extends ElasticsearchRepository<Posts, String> {
}
