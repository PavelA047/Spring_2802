package com.example.lesson_4.dto;

import com.example.lesson_4.persist.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class UserDto {

    private Long id;

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[A-Z]).{8,}$", message = "Password too simple")
    @NotBlank
    private String password;

    @JsonIgnore
    private String matchingPassword;

    private Set<Role> roleSet;

    public UserDto() {
    }

    public UserDto(Long id, String username, String email, String password, Set<Role> roleSet) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleSet = roleSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}
