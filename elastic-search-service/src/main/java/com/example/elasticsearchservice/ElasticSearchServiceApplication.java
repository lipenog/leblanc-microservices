package com.example.elasticsearchservice;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

@SpringBootApplication
public class ElasticSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchServiceApplication.class, args);
    }

    @PostConstruct
    public void test() {
        String path = "./Download.mp4";
        processVideoFile(path);
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
    public void processVideoFile(String videoPath) {
        String audioPath = convertVideoToMP3(videoPath);
        String text = convertAudioToText(audioPath);
        System.out.println(text);
    }
}
