package com.example.usersservice.web.service;

import com.example.usersservice.exception.DuplicateKeyException;
import com.example.usersservice.web.dto.UsersDTO;
import com.example.usersservice.web.entity.Users;
import com.example.usersservice.web.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
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
                .password(usersDTO.getPassword())
                .identifier(usersDTO.getIdentifier())
                .build();
        return usersRepository.save(users);
    }
}
