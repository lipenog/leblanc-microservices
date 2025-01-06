package com.example.elasticsearchservice.web.consumer;

import com.example.elasticsearchservice.web.dto.PostsDTO;
import com.example.elasticsearchservice.web.service.PostsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
public class PostsTopicConsumer {
    private final PostsService postsService;

    public PostsTopicConsumer(PostsService postsService) {
        this.postsService = postsService;
    }

    @KafkaListener(topics = "posts-topic", groupId = "group-id")
    public void consume(String post) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PostsDTO postsDTO = objectMapper.readValue(post, PostsDTO.class);
        postsService.persistPost(postsDTO);
    }
}
