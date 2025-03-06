package com.example.postsservice.web.dto;

import com.example.postsservice.web.entity.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class PostsDTO {
    private final Long id;
    private final UsersDTO usersDTO;
    private final String content;
    private final LocalDateTime publishedAt;
    private final Set<MediaDTO> media;
    public PostsDTO(Posts posts, UsersDTO usersDTO){
        this.id = posts.getId();
        this.usersDTO = usersDTO;
        this.content = posts.getContent();
        this.publishedAt = posts.getPublishedAt();
        this.media = posts.getMediaSet().stream().map(MediaDTO::new).collect(Collectors.toSet());
    }
}
