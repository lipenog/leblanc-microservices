package com.example.elasticsearchservice.web.controller;

import com.example.elasticsearchservice.web.entity.Posts;
import com.example.elasticsearchservice.web.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PostsController {
    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/search")
    public ResponseEntity<SearchHits<Posts>> searchPosts(@RequestParam String content){
        return ResponseEntity.ok(postsService.searchPosts(content));
    }
}
