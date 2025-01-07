package com.example.elasticsearchservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@AllArgsConstructor
public class PostsSearchDTO {
    private PostsSearchQueryDTO query;
    private Set<PostsSearchHitDTO> hits;
    @Getter @Setter
    @AllArgsConstructor
    public static class PostsSearchQueryDTO {
        private Set<String> matches;
        private Set<String> tags;
        private Set<String> phrases;
    }
    @Getter @Setter
    @AllArgsConstructor
    public static class PostsSearchHitDTO{
        private float score;
        private String postsDTO;
    }
}
