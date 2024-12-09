package com.example.postsservice.web.dto;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class UsersDTO {
    private Long id;
    private String identifier;
    private String name;
    private String password;
    private LocalDate creation;
}
