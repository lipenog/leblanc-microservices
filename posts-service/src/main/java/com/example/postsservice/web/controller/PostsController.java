package com.example.postsservice.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {
    @GetMapping("/posts")
    public String post(){
        return "Hello, Post!";
    }
}
