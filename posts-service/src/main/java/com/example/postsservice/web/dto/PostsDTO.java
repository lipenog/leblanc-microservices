package com.example.postsservice.web.dto;

import com.example.postsservice.web.entity.Posts;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class PostsDTO {
    private final Long id;
    private final String content;
    private final Set<MediaDTO> media;
    public PostsDTO(Posts posts){
        this.id = posts.getId();
        this.content = posts.getContent();
        this.media = posts.getMediaSet().stream().map(MediaDTO::new).collect(Collectors.toSet());
    }
}
