package com.example.postsservice.web.service;

import com.example.postsservice.web.entity.Posts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostsTopicService {
    @Value("${constants.posts-topic}")
    private String postTopic;
    private final KafkaTemplate<String, Posts> postsTopicTemplate;

    public PostsTopicService(KafkaTemplate<String, Posts> postsTopicTemplate) {
        this.postsTopicTemplate = postsTopicTemplate;
    }

    public void produceToPostsTopic(Posts posts) {
        postsTopicTemplate.send(postTopic, posts);
        postsTopicTemplate.flush();
    }
}
