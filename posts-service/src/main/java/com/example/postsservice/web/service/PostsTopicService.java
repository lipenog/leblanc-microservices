package com.example.postsservice.web.service;

import com.example.postsservice.web.dto.PostsDTO;
import com.example.postsservice.web.entity.Posts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

@Service
public class PostsTopicService {
    @Value("${constants.posts-topic}")
    private String postTopic;
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;
    private final KafkaTemplate<String, PostsDTO> kafkaTemplate;
    @PostConstruct
    private void test(){
        System.out.println(kafkaServer);
        System.out.println(kafkaServer);
        System.out.println(kafkaServer);
    }

    public PostsTopicService(KafkaTemplate<String, PostsDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceToPostsTopic(PostsDTO posts) {
        kafkaTemplate.send(postTopic, posts);
    }
}
