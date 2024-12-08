package com.example.postsservice.web.dto;

import com.example.postsservice.web.entity.Media;
import lombok.Getter;

@Getter
public class MediaDTO {
    private final Long id;
    private final String mediaPath;
    private final String mediaType;
    public MediaDTO(Media media){
        this.id = media.getId();
        this.mediaPath = media.getMediaPath();
        this.mediaType = media.getMediaType();
    }
}
