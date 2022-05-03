package com.example.lesson_4.dto;

import com.example.lesson_4.persist.User;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RoleDto {

    private Long id;

    private String roleName;

    private Set<User> userList = new HashSet<>();

    public RoleDto(Long id, String roleName, Set<User> userList) {
        this.id = id;
        this.roleName = roleName;
        this.userList = userList;
    }

    public Long getId() {
        return id;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto role = (RoleDto) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
