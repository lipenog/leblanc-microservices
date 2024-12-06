package com.example.usersservice.web.dto;

import com.example.usersservice.web.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UsersPageDTO {
    private int pages;
    private List<UsersDTO> users;
    public UsersPageDTO(Page<Users> page){
        this.pages = page.getTotalPages();
        this.users = page.get().map(UsersDTO::new).collect(Collectors.toList());
    }
}
