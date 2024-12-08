package com.example.postsservice.web.controller;

import com.example.postsservice.web.dto.PostsDTO;
import com.example.postsservice.web.entity.Posts;
import com.example.postsservice.web.service.PostsService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
public class PostsController {
    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/posts")
    public ResponseEntity<PostsDTO> createPost(@RequestPart String content, @RequestPart List<MultipartFile> media) throws IOException {
        String loggedUserIdentifier = SecurityContextHolder.getContext().getAuthentication().getName();
        Posts posts = postsService.createPosts(loggedUserIdentifier, content, media);
        return new ResponseEntity<>(new PostsDTO(posts), HttpStatus.CREATED);
    }
}
