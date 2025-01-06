package com.example.elasticsearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableElasticsearchRepositories
@EntityScan(basePackages = "com.example.elasticsearchservice.web.entity")
@EnableKafka
public class ElasticSearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchServiceApplication.class, args);
    }
}
