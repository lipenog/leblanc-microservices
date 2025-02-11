package com.example.elasticsearchservice.web.controller;

import com.example.elasticsearchservice.web.dto.PostsSearchDTO;
import com.example.elasticsearchservice.web.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {
    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/search")
    public ResponseEntity<PostsSearchDTO> searchPosts(@RequestParam String content){
        return ResponseEntity.ok(postsService.searchPosts(content));
    }
}
