package com.example.usersservice.web.service;

import com.example.usersservice.exception.DuplicateKeyException;
import com.example.usersservice.web.dto.UsersDTO;
import com.example.usersservice.web.entity.Users;
import com.example.usersservice.web.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    @Value(value = "${constants.media-path}")
    private String mediaPath;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<Users> getById(Long id){
        return usersRepository.findById(id);
    }
    public Optional<Users> getByIdentifier(String identifier){
        return usersRepository.findByIdentifier(identifier);
    }
    public Users createUser(UsersDTO usersDTO){
        if(usersRepository.findByIdentifier(usersDTO.getIdentifier()).isPresent()) throw new DuplicateKeyException("The identifier informed is already used");

        Users users = Users.builder()
                .name(usersDTO.getName())
                .password(passwordEncoder.encode(usersDTO.getPassword()))
                .identifier(usersDTO.getIdentifier())
                .build();
        return usersRepository.save(users);
    }

    public Users updateUser(UsersDTO usersDTO, Users original) {
        Users users = Users.builder()
                .id(original.getId())
                .name(usersDTO.getName())
                .identifier(original.getIdentifier())
                .creation(original.getCreation())
                .password(original.getPassword())
                .build();
        return usersRepository.save(users);
    }

    public Users updateUserImage(Users users, MultipartFile image) {
        String imagePath = saveUserImage(image);
        users.setImage(imagePath);
        return usersRepository.save(users);
    }

    public Page<Users> searchUsers(String content, int page){
        Pageable pageable = PageRequest.of(page, 20);
        return usersRepository.findAllByNameLikeOrIdentifierLike("%" + content + "%", "%" + content + "%", pageable);
    }

    private String saveUserImage(MultipartFile media){
        String originalFileName = media.getOriginalFilename();
        // creates new name
        String fileName = UUID.randomUUID().toString();
        // copy original file type
        String fileExtension = originalFileName.substring(originalFileName.indexOf("."));
        fileName = fileName + fileExtension;

        // save media in disk
        saveMedia(media, fileName);

        // creates entity
        return fileName;
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
