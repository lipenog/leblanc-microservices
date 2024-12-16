package com.example.postsservice.web.service;

import com.example.postsservice.web.dto.UsersDTO;
import com.example.postsservice.web.entity.Media;
import com.example.postsservice.web.entity.Posts;
import com.example.postsservice.web.proxy.UserServiceProxy;
import com.example.postsservice.web.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final PostsTopicService postsTopicService;
    private final UserServiceProxy userServiceProxy;
    @Value(value = "${constants.media-path}")
    private String mediaPath;

    @Autowired
    public PostsService(PostsRepository postsRepository, PostsTopicService postsTopicService, UserServiceProxy userServiceProxy) {
        this.postsRepository = postsRepository;
        this.postsTopicService = postsTopicService;
        this.userServiceProxy = userServiceProxy;
    }

    public Posts createPosts(String loggedUserIdentifier, String content, List<MultipartFile> media) {
        UsersDTO users = userServiceProxy.getUserByIdentifier(loggedUserIdentifier);
        Posts posts = Posts.builder()
                .userId(users.getId())
                .content(content)
                .mediaSet(media.stream().map(this::createMedia).collect(Collectors.toSet()))
                .build();
        return postsRepository.save(posts);
    }

    private Media createMedia(MultipartFile media){
        String originalFileName = media.getOriginalFilename();
        // creates new name
        String fileName = UUID.randomUUID().toString();
        // copy original file type
        String fileExtension = originalFileName.substring(originalFileName.indexOf("."));
        fileName = fileName + fileExtension;

        // save media in disk
        saveMedia(media, fileName);

        // creates entity
        return Media.builder()
                .mediaPath(mediaPath + fileName)
                .mediaType(fileExtension)
                .build();
    }

    private void saveMedia(MultipartFile media, String fileName) {
        // concat media path with file name
        Path path = Path.of(mediaPath, fileName);
        // saves the image
        try {
            Files.copy(media.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Unable to copy file " + media.getOriginalFilename());
        }
    }
}
