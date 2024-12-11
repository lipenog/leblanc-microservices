package com.example.elasticsearchservice;


import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.Repository;

public interface ElasticRepository extends ElasticsearchRepository<Posts, String> {
}
