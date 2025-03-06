package com.example.postsservice.web.service;

import com.example.postsservice.web.dto.PostsSearchDTO;
import com.example.postsservice.web.dto.UsersDTO;
import com.example.postsservice.web.entity.Media;
import com.example.postsservice.web.entity.Posts;
import com.example.postsservice.web.proxy.ElasticsearchService;
import com.example.postsservice.web.proxy.UserServiceProxy;
import com.example.postsservice.web.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;


@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final ElasticsearchService elasticsearchService;

    @Value(value = "${constants.media-path}")
    private String mediaPath;

    @Autowired
    public PostsService(PostsRepository postsRepository, ElasticsearchService elasticsearchService) {
        this.postsRepository = postsRepository;
        this.elasticsearchService = elasticsearchService;
    }

    public Set<Posts> getPostsByUserID(Long userID) {
        return postsRepository.getPostsByUserId(userID);
    }

    public Set<Posts> searchPosts(String content) {
        // returns all posts if the search is empty
        if(isNull(content) || isBlank(content)) {
            return new HashSet<>(postsRepository.findAll());
        }
        // calls search service
        PostsSearchDTO searchResult = elasticsearchService.searchPosts(content);
        // maps the dto to posts
        return searchResult
                .getHits()
                .stream()
                .map(postsSearchHitDTO -> {
                    Optional<Posts> posts = postsRepository.findById(Long.valueOf(postsSearchHitDTO.getPostsDTO()));
                    return posts.orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
    public Posts createPosts(long userID, String content, List<MultipartFile> media) {
        Posts posts = Posts.builder()
                .userId(userID)
                .content(content)
                .mediaSet(media.stream().map(this::createMedia).collect(Collectors.toSet()))
                .build();
        return postsRepository.save(posts);
    }

    private Media createMedia(MultipartFile media){
        String originalFileName = media.getOriginalFilename();
        // creates new name
        String fileName = UUID.randomUUID().toString();
        // copy original file type
        String fileExtension = originalFileName.substring(originalFileName.indexOf("."));
        fileName = fileName + fileExtension;

        // save media in disk
        saveMedia(media, fileName);

        // creates entity
        return Media.builder()
                .mediaPath(mediaPath + fileName)
                .mediaType(fileExtension)
                .build();
    }

    private void saveMedia(MultipartFile media, String fileName) {
        // concat media path with file name
        Path path = Path.of(mediaPath, fileName);
        // saves the image
        try {
            Files.copy(media.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Unable to copy file " + media.getOriginalFilename());
        }
    }
}
