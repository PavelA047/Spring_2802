package com.example.lesson_4.controller;

import com.example.lesson_4.persist.User;
import org.springframework.data.jpa.domain.Specification;

public final class UserSpecifications {

    public static Specification<User> usernameContaining(String username) {
        return (root, query, cb) -> cb.like(root.get("username"), "%" + username + "%");
    }

    public static Specification<User> emailContaining(String email) {
        return (root, query, cb) -> cb.like(root.get("email"), "%" + email + "%");
    }
}
