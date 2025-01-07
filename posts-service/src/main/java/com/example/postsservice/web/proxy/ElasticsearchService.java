package com.example.postsservice.web.proxy;

import com.example.postsservice.web.dto.PostsSearchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("elastic-search-service")
public interface ElasticsearchService {
    @GetMapping("/search")
    PostsSearchDTO searchPosts(@RequestParam String content);
}
