package com.example.elasticsearchservice;

import com.example.elasticsearchservice.web.dto.PostsDTO;
import com.example.elasticsearchservice.web.entity.Posts;
import com.example.elasticsearchservice.web.repository.ElasticRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

@SpringBootApplication
@EnableElasticsearchRepositories
@EnableKafka
public class ElasticSearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchServiceApplication.class, args);
    }
}
