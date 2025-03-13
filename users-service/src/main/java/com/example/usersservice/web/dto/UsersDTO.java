package com.example.usersservice.web.dto;

import com.example.usersservice.web.entity.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.util.Objects.nonNull;

@NoArgsConstructor @AllArgsConstructor
@Getter
public class UsersDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank(message = "The identifier must be valid") @NotNull(message = "The identifier must be valid") @Pattern(regexp = "([a-zA-Z0-9]|-|_|)*", message = "The identifier must be valid")
    @Size(min = 5, max = 50, message = "The identifier must be valid")
    private String identifier;
    @NotBlank(message = "The name must be valid") @NotNull(message = "The name must be valid") @Pattern(regexp = "([a-zA-Z0-9]|-|_|\\s|)*", message = "The name must be valid")
    @Size(min = 3, max = 50, message = "The name must be valid")
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}", message = "The password must be valid")
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate creation;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String image;

    public UsersDTO(Users users){
        this.id = users.getId();
        this.name = users.getName();
        this.identifier = users.getIdentifier();
        this.creation = users.getCreation();
        // hardcoded my bad
        if(nonNull(users.getImage())) {
            this.image = "http://localhost:9000/media/" + users.getImage();
        }
    }
}
