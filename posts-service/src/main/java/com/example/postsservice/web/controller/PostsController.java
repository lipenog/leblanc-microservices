package com.example.postsservice.web.controller;

import com.example.postsservice.web.dto.PostsDTO;
import com.example.postsservice.web.dto.UsersDTO;
import com.example.postsservice.web.entity.Posts;
import com.example.postsservice.web.proxy.UserServiceProxy;
import com.example.postsservice.web.service.PostsService;
import com.example.postsservice.web.service.PostsTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PostsController {
    private final PostsService postsService;
    private final PostsTopicService postsTopicService;
    private final UserServiceProxy userServiceProxy;
    @Autowired
    public PostsController(PostsService postsService, PostsTopicService postsTopicService, UserServiceProxy userServiceProxy) {
        this.postsService = postsService;
        this.postsTopicService = postsTopicService;
        this.userServiceProxy = userServiceProxy;
    }

    @PostMapping("/posts")
    public ResponseEntity<PostsDTO> createPost(@RequestPart String content, @RequestPart(required = false) List<MultipartFile> media) throws IOException {
        String loggedUserIdentifier = SecurityContextHolder.getContext().getAuthentication().getName();
        UsersDTO loggedUser = userServiceProxy.getUserByIdentifier(loggedUserIdentifier);
        Posts posts = postsService.createPosts(loggedUserIdentifier, content, media);
        PostsDTO postsDTO = new PostsDTO(posts, loggedUser);
        postsTopicService.produceToPostsTopic(postsDTO);
        return new ResponseEntity<>(postsDTO, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<Set<PostsDTO>> searchPosts(@RequestParam String content) {
        Set<Posts> searchResult = postsService.searchPosts(content);
        Set<PostsDTO> searchResponse = searchResult
                .stream()
                .map(posts -> {
                    UsersDTO usersDTO = userServiceProxy.getUserById(posts.getUserId());
                    return new PostsDTO(posts, usersDTO);
                }).collect(Collectors.toSet());
        return ResponseEntity.ok(searchResponse);
    }
}
