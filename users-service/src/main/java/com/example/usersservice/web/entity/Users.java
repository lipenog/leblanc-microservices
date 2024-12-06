package com.example.usersservice.web.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Users {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    private String name;
    private String password;
    private LocalDate creation;
    @PrePersist
    private void setDate(){
        this.creation = LocalDate.now(ZoneId.of("UTC"));
    }
}
