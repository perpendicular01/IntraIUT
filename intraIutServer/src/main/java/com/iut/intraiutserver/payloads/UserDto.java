package com.iut.intraiutserver.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "Name must be a minimum of 4 characters.")
    private String name;

    @Email(message = "Email address is not valid.")
    @NotEmpty(message = "Email is required.")
    private String email;

    @NotEmpty
    // --- IMPROVED VALIDATION RULE ---

    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

}