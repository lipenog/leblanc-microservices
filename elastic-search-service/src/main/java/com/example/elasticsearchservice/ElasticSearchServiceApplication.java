package com.example.elasticsearchservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

@SpringBootApplication
@EnableElasticsearchRepositories
public class ElasticSearchServiceApplication {
    @Autowired
    public ElasticRepository elasticRepository;
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchServiceApplication.class, args);
    }
    @KafkaListener(topics = "posts-topic", groupId = "group-id")
    public void consume(String post) throws JsonProcessingException {
        System.out.println(post);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PostsDTO postsDTO = objectMapper.readValue(post, PostsDTO.class);
        System.out.println(postsDTO.getUserId());
    }

    public void test() {
        String path = "./Download.mp4";
        String result = processVideoFile(path);
        Posts posts = elasticRepository.save(new Posts(UUID.randomUUID().toString(), "Teste de video", result));
        System.out.println(posts);

        System.out.println("POTS: ");
        elasticRepository.findAll().forEach(System.out::println);
    }

    public String convertVideoToMP3(String originalPath){
        try {
            String outputPath = "./Download.mp3";
            ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-i", originalPath, outputPath);
            Process process = pb.start();
            process.waitFor();
            return outputPath;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String convertAudioToText(String audioPath){
        try {
            ProcessBuilder pb = new ProcessBuilder("/app/venv/bin/python3", "./main.py", audioPath);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            process.waitFor();
            return result.toString();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String processVideoFile(String videoPath) {
        String audioPath = convertVideoToMP3(videoPath);
        return convertAudioToText(audioPath);
    }
}
