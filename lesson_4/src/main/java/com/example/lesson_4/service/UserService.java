package com.example.lesson_4.service;

import com.example.lesson_4.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {

    Page<UserDto> findUsersByFilter(String usernameFilter, String emailFilter, Integer page, Integer size, String sortField);

    Optional<UserDto> findById(long id);

    UserDto save(UserDto user);

    void deleteById(long id);
}
