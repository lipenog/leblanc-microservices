package com.example.postsservice.web.controller;

import com.example.postsservice.web.dto.PostsDTO;
import com.example.postsservice.web.entity.Posts;
import com.example.postsservice.web.service.PostsService;
import com.example.postsservice.web.service.PostsTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PostsController {
    private final PostsService postsService;
    private final PostsTopicService postsTopicService;
    @Autowired
    public PostsController(PostsService postsService, PostsTopicService postsTopicService) {
        this.postsService = postsService;
        this.postsTopicService = postsTopicService;
    }

    @PostMapping("/posts")
    public ResponseEntity<PostsDTO> createPost(@RequestPart String content, @RequestPart List<MultipartFile> media) throws IOException {
        String loggedUserIdentifier = SecurityContextHolder.getContext().getAuthentication().getName();
        Posts posts = postsService.createPosts(loggedUserIdentifier, content, media);
        PostsDTO postsDTO = new PostsDTO(posts);
        postsTopicService.produceToPostsTopic(postsDTO);
        return new ResponseEntity<>(postsDTO, HttpStatus.CREATED);
    }
}
