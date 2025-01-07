package com.example.elasticsearchservice.web.service;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.example.elasticsearchservice.web.dto.MediaDTO;
import com.example.elasticsearchservice.web.dto.PostsDTO;
import com.example.elasticsearchservice.web.dto.PostsSearchDTO;
import com.example.elasticsearchservice.web.entity.Posts;
import com.example.elasticsearchservice.web.repository.ElasticRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PostsService {
    @Value("${constants.audio-path}")
    private String audioPath;
    private final ElasticRepository elasticRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public PostsService(ElasticRepository elasticRepository, ElasticsearchOperations elasticsearchOperations) {
        this.elasticRepository = elasticRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    private String convertVideoToMP3(String originalPath){
        try {
            String outputPath = audioPath + UUID.randomUUID() + ".mp3";
            ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-i", originalPath, outputPath);
            Process process = pb.start();
            process.waitFor();
            return outputPath;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private String convertAudioToText(String audioPath){
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

    private HashMap<String, Set<String>> treatQuery(String query) {
        HashMap<String, Set<String>> treatedQuery = new HashMap<>();
        // extract #(.*) and "(.*)" for exact match

        // extract the tags
        Pattern pattern = Pattern.compile("#(\\S*)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(query);
        Set<String> tags = matcher.results().map(result -> result.group(1)).collect(Collectors.toSet());
        // remove tags from query
        query = matcher.replaceAll("");

        // extract match phrases
        pattern = Pattern.compile("\\Q\"\\E(.*?)\\Q\"\\E",  Pattern.DOTALL);
        matcher = pattern.matcher(query);
        Set<String> matchPhrases = matcher.results().map(result -> result.group(1)).collect(Collectors.toSet());
        // remove match phrases from query
        query = matcher.replaceAll("");

        // treat final query ws
        query = query.replaceAll("\\s+", " ");

        // add treated query elements
        treatedQuery.put("match", Set.of(query));
        treatedQuery.put("tags", tags);
        treatedQuery.put("phrases", matchPhrases);

        return treatedQuery;
    }

    private Query buildSearchQuery(HashMap<String, Set<String>> treatedQuery){
        BoolQuery.Builder boolQuery = QueryBuilders.bool();

        Set<String> matches = treatedQuery.get("match");
        Set<String> tags = treatedQuery.get("tags");
        Set<String> phrases = treatedQuery.get("phrases");

        // add matches should queries
        matches.forEach(match -> {
            boolQuery.should(should -> should.match(matchQuery -> matchQuery.field("content").query(match)));
            boolQuery.should(should -> should.match(matchQuery -> matchQuery.field("media_content").query(match)));
        });

        // add tags should queries
        tags.forEach(tag -> {
            boolQuery.should(should -> should.matchPhrase(matchPhrase -> matchPhrase.field("content").query(tag)));
        });

        // add phrases queries
        phrases.forEach(phrase -> {
            boolQuery.should(should -> should.matchPhrase(matchPhrase -> matchPhrase.field("content").query(phrase)));
            boolQuery.should(should -> should.matchPhrase(matchPhrase -> matchPhrase.field("media_content").query(phrase)));
        });

        // build and return query
        return NativeQuery.builder()
                .withQuery(boolQuery.build()._toQuery())
                .build();
    }

    public PostsSearchDTO searchPosts(String queryContent){
        HashMap<String, Set<String>> treatedQuery = treatQuery(queryContent);
        Query elasticQuery = buildSearchQuery(treatedQuery);
        SearchHits<Posts> hits = elasticsearchOperations.search(elasticQuery, Posts.class);
        // create Query DTO
        PostsSearchDTO.PostsSearchQueryDTO queryDTO = new PostsSearchDTO.PostsSearchQueryDTO(
                treatedQuery.get("match"),
                treatedQuery.get("tags"),
                treatedQuery.get("phrases"));

        // parse hits results and map to DTO
        Set<PostsSearchDTO.PostsSearchHitDTO> hitsDTO = hits.get().map(hit -> {
            Posts postsHit = hit.getContent();
            float score = hit.getScore();
            return new PostsSearchDTO.PostsSearchHitDTO(score, postsHit.getId());
        }).collect(Collectors.toSet());

        return new PostsSearchDTO(queryDTO, hitsDTO);
    }


    public void persistPost(PostsDTO postsDTO) {
        // filters only mp4 files path
        List<String> postMP4FilesPath = postsDTO.getMedia()
                .stream()
                .filter(mediaDTO -> mediaDTO.getMediaType().equals(".mp4"))
                .map(MediaDTO::getMediaPath)
                .toList();
        postMP4FilesPath.forEach(System.out::println);
        // appends every media
        StringBuilder mediaContent = new StringBuilder();
        postMP4FilesPath.forEach(mp4FilePath -> {
            String videoText = processVideoFile(mp4FilePath);
            mediaContent.append(videoText);
        });

        // persists in elastic
        Posts posts = new Posts(String.valueOf(postsDTO.getId()), postsDTO.getContent(), mediaContent.toString());
        elasticRepository.save(posts);
    }
}
