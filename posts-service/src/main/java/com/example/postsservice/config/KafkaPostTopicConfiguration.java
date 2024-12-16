package com.example.postsservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPostTopicConfiguration {
    @Value("${constants.posts-topic}")
    private String postTopic;
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(postTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
